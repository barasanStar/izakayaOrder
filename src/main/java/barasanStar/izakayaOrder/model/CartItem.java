package barasanStar.izakayaOrder.model;

public class CartItem {
	private Long menuId;
	private int quantity;

	public CartItem(Long menuId, int quantity) {
		this.menuId = menuId;
		this.quantity = quantity;
	}

	public Long getMenuId() {
		return menuId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
