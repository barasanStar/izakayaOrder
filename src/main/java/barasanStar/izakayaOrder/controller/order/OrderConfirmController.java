package barasanStar.izakayaOrder.controller.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import barasanStar.izakayaOrder.model.Cart;
import barasanStar.izakayaOrder.service.OrderService;
import barasanStar.izakayaOrder.util.DebugLogger;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/order")
public class OrderConfirmController {

	@Autowired
	private OrderService orderService;

	@PostMapping("/confirm")
	public String confirmOrder(HttpSession session) {
		Cart cart = (Cart) session.getAttribute("cart");
		Long customerId = (Long) session.getAttribute("customerId");

		orderService.confirmOrder(cart, customerId);

		DebugLogger.log(OrderConfirmController.class, "カートに商品があります。注文を確定します");
		session.setAttribute("cart", new Cart());
		return "redirect:/order/complete";
	}
}
