package barasanStar.izakayaOrder.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import barasanStar.izakayaOrder.model.Cart;
import barasanStar.izakayaOrder.model.CartItem;
import barasanStar.izakayaOrder.model.entity.Menu;
import barasanStar.izakayaOrder.repository.MenuRepository;
import barasanStar.izakayaOrder.service.CartService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
public class CartController {
	@Autowired
	private MenuRepository menuRepository;

	@Autowired
	private CartService cartService;

	@PostMapping("/add")
	public String addToCart(@RequestParam("menuId") Long menuId,
			@RequestParam("quantity") int quantity, HttpSession session) {
		Cart cart = cartService.getOrCreateCart(session);
		cart.addItem(menuId, quantity);
		return "redirect:/cart/view";
	}

	@GetMapping("/view")
	public String viewCart(Model model, HttpSession session) {
		Cart cart = cartService.getOrCreateCart(session);
		Map<Long, String> menuNameMap = cartService.createMenuNameMap(cart.getItems());
		model.addAttribute("cart", cart);
		model.addAttribute("menuNameMap", menuNameMap);
		return "cart/view";
	}

	@GetMapping("/edit")
	public String editCartItem(@RequestParam Long menuId, Model model, HttpSession session) {
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart == null) {
			return "redirect:/cart/view";
		}

		CartItem target = cart.getItems().stream()
				.filter(item -> item.getMenuId().equals(menuId))
				.findFirst()
				.orElse(null);

		if (target == null) {
			return "redirect:/cart/view";
		}

		Menu menu = menuRepository.findById(menuId).orElse(null);
		model.addAttribute("item", target);
		model.addAttribute("menu", menu);
		model.addAttribute("cart", cart);
		return "cart/edit";
	}

	@PostMapping("/update")
	public String updateCartItem(@RequestParam Long menuId,
			@RequestParam int quantity,
			HttpSession session) {
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart != null) {
			cart.updateItem(menuId, quantity);
		}
		return "redirect:/cart/view";
	}

	@PostMapping("/remove")
	public String removeCartItem(@RequestParam Long menuId, HttpSession session) {
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart != null) {
			cart.removeItem(menuId);
		}
		return "redirect:/cart/view";
	}

}
