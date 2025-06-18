package barasanStar.izakayaOrder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import barasanStar.izakayaOrder.model.entity.Category;
import barasanStar.izakayaOrder.model.entity.Menu;
import barasanStar.izakayaOrder.service.CategoryService;
import barasanStar.izakayaOrder.service.MenuService;

@Controller
public class MenuController {
	@Autowired
	private MenuService menuService;

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/categories/{id}")
	public String showMenuList(@PathVariable("id") Long categoryId, Model model) {
		List<Menu> menus = menuService.findByCategoryId(categoryId);
		Category category = categoryService.findById(categoryId);
		model.addAttribute("menus", menus);
		model.addAttribute("categoryName", category.getName());
		return "menu-list";
	}

	@GetMapping("/menus/{id}")
	public String showMenuDetail(@PathVariable("id") Long menuId, Model model) {
		Menu menu = menuService.findById(menuId);
		model.addAttribute("menu", menu);
		return "menu-detail";
	}
}
