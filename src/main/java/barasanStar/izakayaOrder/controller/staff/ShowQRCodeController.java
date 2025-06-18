package barasanStar.izakayaOrder.controller.staff;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/staff")
public class ShowQRCodeController {

	@GetMapping("show-QR-code")
	public String showQRCode() {
		return "staff/show-QR-code";
	}

}
