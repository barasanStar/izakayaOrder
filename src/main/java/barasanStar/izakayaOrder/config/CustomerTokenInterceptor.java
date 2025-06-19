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
		// グループトークンをリクエストパラメータから取得
		String groupToken = request.getParameter("groupToken");
		DebugLogger.log(CustomerTokenInterceptor.class, "■■■groupToken=" + groupToken);

		boolean hasGroupToken = true;
		if (groupToken == null) {
			hasGroupToken = false;
		}

		boolean hasCustomerToken = true;
		Object customerTokenObj = session.getAttribute("customerToken");
		if (customerTokenObj == null) {
			hasCustomerToken = false;
		}

		if (!hasGroupToken) {
			if (hasCustomerToken) {
				// OK、何もしない。
				return true;
			} else {
				// 不正、弾く。
				response.sendError(HttpServletResponse.SC_FORBIDDEN, "無効なトークンです。");
				return false;
			}
		} else {
			// 【ここに入ってくるということは、groupTokenを持っている】
			if (!validityGroupToken(groupToken)) {
				// 不正、弾く。
				response.sendError(HttpServletResponse.SC_FORBIDDEN, "無効なトークンです。");
				return false;
			} else {
				// 【ここに入ってくるということは、有効なgroupToken】
				Optional<VisitGroup> optionalGroup = visitGroupRepository.findByGroupToken(groupToken);
				VisitGroup group = optionalGroup.get();

				if (hasCustomerToken) {
					String customerToken = (String) customerTokenObj;
					Optional<Long> optionalGroupId = customerRepository
							.findActiveVisitGroupIdByCustomerToken(customerToken);

					if (optionalGroupId.isPresent()) {
						Long groupIdRelatedByCustomerToken = optionalGroupId.get();
						long groupId = group.getId();
						// customerTokenとgroupTokenのgroupID整合性が取れていればOK
						if (groupId == groupIdRelatedByCustomerToken) {
							// OK、何もしない。
							return true;
						} else {
							// groupTokenを正として、customerTokenを再発行
							registerCustomer(group, session);
							return true;
						}
					} else {
						// groupTokenを正として、customerTokenを再発行
						registerCustomer(group, session);
						return true;
					}
				} else {
					// customerTokenを発行
					registerCustomer(group, session);
					return true;
				}
			}
		}
	}

	private void registerCustomer(VisitGroup group, HttpSession session) {
		Customer customer = new Customer();
		String customerToken = UUID.randomUUID().toString();
		customer.setCustomerToken(customerToken);
		customer.setVisitGroup(group);
		customerRepository.save(customer);
		session.setAttribute("customerId", customer.getId());
		session.setAttribute("visitGroupId", group.getId());
		session.setAttribute("customerToken", customerToken);
		DebugLogger.log(CustomerTokenInterceptor.class, "■■■顧客登録");
		DebugLogger.log(CustomerTokenInterceptor.class, "■■■customer: " + customer);
		DebugLogger.log(CustomerTokenInterceptor.class, "■■■customerToken: " + customerToken);
	}

	// グループトークンの有効/無効を判定
	private boolean validityGroupToken(String groupToken) {
		Optional<VisitGroup> optionalGroup = visitGroupRepository.findByGroupToken(groupToken);
		if (optionalGroup.isEmpty()) {
			return false;
		}
		if (!optionalGroup.get().getStatus().equals(GroupTokenStatus.ACTIVE)) {
			return false;
		}
		return true;
	}
}
