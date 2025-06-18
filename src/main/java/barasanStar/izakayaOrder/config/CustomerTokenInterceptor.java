package barasanStar.izakayaOrder.config;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import barasanStar.izakayaOrder.enums.GroupTokenStatus;
import barasanStar.izakayaOrder.model.entity.Customer;
import barasanStar.izakayaOrder.model.entity.VisitGroup;
import barasanStar.izakayaOrder.repository.CustomerRepository;
import barasanStar.izakayaOrder.repository.VisitGroupRepository;
import barasanStar.izakayaOrder.util.DebugLogger;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

// Interceptorであまり重い処理（集計、JOIN含む読込など）は避け、
// 「認証・識別」に特化させるのがベストです。
// あくまで「そのリクエストを処理する前提を整える層」なので、
// 必要最小限の準備情報をセッションに載せる程度が最適です。
@Component
public class CustomerTokenInterceptor implements HandlerInterceptor {

	@Autowired
	private VisitGroupRepository visitGroupRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response,
			Object handler) throws Exception {

		HttpSession session = request.getSession();
		DebugLogger.log(CustomerTokenInterceptor.class, "customerToken＝" + session.getAttribute("customerToken"));

		if (session.getAttribute("customerToken") == null) {
			// 初回アクセス時の処理
			String customerToken = UUID.randomUUID().toString();
			session.setAttribute("customerToken", customerToken);

			// VisitGroupのトークンはURLから取得
			String groupToken = request.getParameter("groupToken");
			Optional<VisitGroup> optionalGroup = visitGroupRepository.findByGroupToken(groupToken);

			DebugLogger.log(CustomerTokenInterceptor.class, "optionalGroup=" + optionalGroup);

			if (optionalGroup.isEmpty()
					|| !optionalGroup.get().getStatus().equals(GroupTokenStatus.ACTIVE)) {
				response.sendError(HttpServletResponse.SC_FORBIDDEN, "無効なトークンです。");
				return false;
			}
			VisitGroup group = optionalGroup.get();
			DebugLogger.log(CustomerTokenInterceptor.class, "■初回確認■group=" + group);

			// Customer登録
			Customer customer = new Customer();
			customer.setCustomerToken(customerToken);
			customer.setVisitGroup(group);
			customerRepository.save(customer);

			session.setAttribute("customerId", customer.getId());
			session.setAttribute("customerToken", customerToken);
			session.setAttribute("tableNumber", group.getTableNumber());
			session.setAttribute("visitGroupId", group.getId());

			DebugLogger.log(CustomerTokenInterceptor.class, "■■■初回のアクセス");
			DebugLogger.log(CustomerTokenInterceptor.class, "■■■customer: " + customer);
			DebugLogger.log(CustomerTokenInterceptor.class, "■■■customerToken: " + customerToken);
			DebugLogger.log(CustomerTokenInterceptor.class, "■■■groupToken: " + groupToken);
		}

		return true; // 通常の処理へ進む
	}
}
