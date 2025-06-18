package barasanStar.izakayaOrder.controller.staff;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/staff")
public class StartVisitGroupController {

	@GetMapping("/start-visit-group")
	public String start() {
		return "staff/start-visit-group";
	}
}
