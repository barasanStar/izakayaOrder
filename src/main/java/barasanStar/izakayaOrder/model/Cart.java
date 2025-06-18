package barasanStar.izakayaOrder.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	private List<CartItem> items = new ArrayList<>();

	public void addItem(Long menuId, int quantity) {
		for (CartItem item : items) {
			if (item.getMenuId().equals(menuId)) {
				item.setQuantity(item.getQuantity() + quantity);
				return;
			}
		}
		items.add(new CartItem(menuId, quantity));
	}

	public List<CartItem> getItems() {
		return items;
	}

	public void updateItem(Long menuId, int quantity) {
		for (CartItem item : items) {
			if (item.getMenuId().equals(menuId)) {
				item.setQuantity(quantity);
				return;
			}
		}
	}

	public void removeItem(Long menuId) {
		items.removeIf(item -> item.getMenuId().equals(menuId));
	}

}
