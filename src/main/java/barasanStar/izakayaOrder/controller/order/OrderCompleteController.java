package barasanStar.izakayaOrder.controller.order;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderCompleteController {

	@GetMapping("/complete")
	public String showOrderComplete() {
		return "order/complete"; // → templates/order/complete.html を表示
	}
}
