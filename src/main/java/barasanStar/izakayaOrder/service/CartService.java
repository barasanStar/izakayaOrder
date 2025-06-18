package barasanStar.izakayaOrder.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import barasanStar.izakayaOrder.model.Cart;
import barasanStar.izakayaOrder.model.CartItem;
import barasanStar.izakayaOrder.repository.MenuRepository;
import barasanStar.izakayaOrder.util.DebugLogger;
import jakarta.servlet.http.HttpSession;

@Service
public class CartService {
	private static final String CART_SESSION_KEY = "cart";

	@Autowired
	private MenuRepository menuRepository;

	private Map<String, List<CartItem>> cartMap = new HashMap<>();

	// セッションからカートを取得 or 新規作成
	public Cart getOrCreateCart(HttpSession session) {
		Cart cart = (Cart) session.getAttribute(CART_SESSION_KEY);
		if (cart == null) {
			cart = new Cart();
			session.setAttribute(CART_SESSION_KEY, cart);
		}
		return cart;
	}

	public List<CartItem> getCart(String sessionValue) {
		DebugLogger.log(CartService.class, "[getCart] this = " + this);
		DebugLogger.log(CartService.class, "[getCart] sessionValue = " + sessionValue);
		return cartMap.computeIfAbsent(sessionValue, k -> new ArrayList<>());
	}

	public void addToCart(String sessionValue, CartItem item) {
		DebugLogger.log(CartService.class, "[addToCart] this = " + this);
		DebugLogger.log(CartService.class, "[addToCart] sessionValue = " + sessionValue);
		DebugLogger.log(CartService.class,
				"[addToCart] menuId = " + item.getMenuId() + ", quantity = " + item.getQuantity());
		cartMap.computeIfAbsent(sessionValue, k -> new ArrayList<>()).add(item);
	}

	public void clearCart(String sessionValue) {
		cartMap.remove(sessionValue);
	}

	public Map<Long, String> createMenuNameMap(List<CartItem> cartItems) {
		Map<Long, String> map = new HashMap<>();
		for (CartItem item : cartItems) {
			menuRepository.findById(item.getMenuId()).ifPresent(menu -> {
				map.put(item.getMenuId(), menu.getName());
			});
		}
		return map;
	}

}
