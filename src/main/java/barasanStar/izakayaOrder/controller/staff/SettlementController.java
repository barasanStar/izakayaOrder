package barasanStar.izakayaOrder.controller.staff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import barasanStar.izakayaOrder.model.entity.VisitGroup;
import barasanStar.izakayaOrder.repository.VisitGroupRepository;
import barasanStar.izakayaOrder.service.OrderService;
import barasanStar.izakayaOrder.service.VisitGroupService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/staff")
public class SettlementController {
	@Autowired
	private VisitGroupRepository visitGroupRepository;

	@Autowired
	private OrderService orderService;

	@Autowired
	private VisitGroupService visitGroupService;

	@GetMapping("/settlement")
	public String showSettlement(HttpServletRequest request, Model model, HttpSession session) {
		Long visitGroupId = Long.parseLong(request.getParameter("visitGroupId"));
		int totalAmount = orderService.calculateGroupTotalCharge(visitGroupId);
		model.addAttribute("totalAmount", totalAmount);

		VisitGroup visitGroup = visitGroupRepository.findById(visitGroupId)
				.orElseThrow(() -> new IllegalArgumentException("無効なグループIDです"));
		model.addAttribute("visitGroup", visitGroup);

		return "/staff/settlement";
	}

	@PostMapping("/settle")
	public String completeSettlement(@RequestParam Long visitGroupId) {
		visitGroupService.setInactive(visitGroupId);
		return "redirect:/staff/settlement-complete";
	}

	@GetMapping("/settlement-complete")
	public String showCompletePage() {
		return "/staff/settlement-complete";
	}

}
