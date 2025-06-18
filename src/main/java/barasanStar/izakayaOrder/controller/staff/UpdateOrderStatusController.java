package barasanStar.izakayaOrder.controller.staff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import barasanStar.izakayaOrder.enums.OrderItemStatus;
import barasanStar.izakayaOrder.service.OrderItemService;

@Controller
@RequestMapping("/staff")
public class UpdateOrderStatusController {
	@Autowired
	private OrderItemService orderItemService;

	@PostMapping("/order/update-status")
	public String updateOrderStatus(@RequestParam Long orderItemId, @RequestParam OrderItemStatus status) {
		orderItemService.updateStatus(orderItemId, status);
		return "redirect:/staff/unserved";
	}
}
