package barasanStar.izakayaOrder.controller.staff;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import barasanStar.izakayaOrder.enums.OrderItemStatus;
import barasanStar.izakayaOrder.model.dto.PendingOrderItemDTO;
import barasanStar.izakayaOrder.repository.OrderItemRepository;

@Controller
@RequestMapping("/staff")
public class UnservedOrdersController {
	@Autowired
	private OrderItemRepository orderItemRepository;

	@GetMapping("/unserved")
	public String showUnservedOrders(Model model) {
		List<PendingOrderItemDTO> pendingOrderDTOs = orderItemRepository.findUnservedItems();
		model.addAttribute("pendingOrderDTOs", pendingOrderDTOs);
		//		model.addAttribute("statuses", OrderItemStatus.values());
		model.addAttribute("availableStatuses", OrderItemStatus.values());
		return "/staff/pending-order-list";
	}
}
