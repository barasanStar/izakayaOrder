package barasanStar.izakayaOrder.controller.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import barasanStar.izakayaOrder.service.OrderHistoryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/order")
public class OrderHistoryController {

	@Autowired
	private OrderHistoryService orderHistoryService;

	@GetMapping("/history")
	public String showOrderHistory(HttpServletRequest request, Model model, HttpSession session) {
		Long customerId = (Long) session.getAttribute("customerId");
		Long visitGroupId = (Long) session.getAttribute("visitGroupId");

		OrderHistoryService.OrderHistoryData data = orderHistoryService.getOrderHistory(customerId, visitGroupId);

		model.addAttribute("orderItemSummaryDTOs", data.getSummaries());
		model.addAttribute("customerTotalCharge", data.getCustomerTotalCharge());
		model.addAttribute("groupTotalCharge", data.getGroupTotalCharge());
		return "order/history";
	}
}
