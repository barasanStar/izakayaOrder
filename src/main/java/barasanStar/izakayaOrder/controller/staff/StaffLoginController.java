package barasanStar.izakayaOrder.controller.staff;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/staff")
public class StaffLoginController {

	@GetMapping("/login")
	public String showLoginForm() {
		return "/staff/login";
	}
}
