package barasanStar.izakayaOrder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import barasanStar.izakayaOrder.service.CategoryService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CategoriesController {
	@Autowired
	private CategoryService categoryService;

	@GetMapping("/")
	public String startOrder() {
		return "redirect:/categories";
	}

	@GetMapping("/categories")
	public String showCategoryList(HttpServletRequest request, Model model) {
		model.addAttribute("categories", categoryService.findAll());
		return "category-list";
	}

}
