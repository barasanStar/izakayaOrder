package barasanStar.izakayaOrder.controller.staff;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import barasanStar.izakayaOrder.config.StaffDetails;

@Controller
@RequestMapping("/staff")
public class StaffHomeController {

	@GetMapping("/home")
	public String showStaffHome(Model model, @AuthenticationPrincipal StaffDetails staffDetails) {
		model.addAttribute("name", staffDetails.getDisplayName());
		return "staff/home";
	}
}
