package barasanStar.izakayaOrder.controller.staff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import barasanStar.izakayaOrder.service.VisitGroupService;

@Controller
@RequestMapping("/staff")
public class CreateVisitGroupController {

	@Autowired
	private VisitGroupService visitGroupService;

	@PostMapping("/create")
	public String createVisitGroup(
			@RequestParam("tableNumber") int tableNumber,
			@RequestParam("numberOfPeople") int numberOfPeople,
			RedirectAttributes redirectAttributes) {
		String groupToken = visitGroupService.registerGroup(tableNumber, numberOfPeople);
		String baseURL = "http://localhost:8080/categories?groupToken=";
		String accessURL = baseURL + groupToken;
		redirectAttributes.addFlashAttribute("accessURL", accessURL);
		return "redirect:/staff/show-QR-code";
	}
}
