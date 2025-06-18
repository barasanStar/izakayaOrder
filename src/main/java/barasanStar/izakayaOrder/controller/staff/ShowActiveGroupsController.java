package barasanStar.izakayaOrder.controller.staff;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import barasanStar.izakayaOrder.model.entity.VisitGroup;
import barasanStar.izakayaOrder.repository.VisitGroupRepository;

@Controller
@RequestMapping("/staff")
public class ShowActiveGroupsController {
	@Autowired
	private VisitGroupRepository visitGroupRepository;

	@GetMapping("/active-visit-groups")
	public String showActiveGroups(Model model) {
		List<VisitGroup> activeVisitGroups = visitGroupRepository.findActiveVisitGroups();
		model.addAttribute("activeVisitGroups", activeVisitGroups);
		return "staff/active-visit-groups";
	}
}
