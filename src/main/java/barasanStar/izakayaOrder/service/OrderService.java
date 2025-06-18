package barasanStar.izakayaOrder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import barasanStar.izakayaOrder.enums.OrderItemStatus;
import barasanStar.izakayaOrder.enums.OrderStatus;
import barasanStar.izakayaOrder.model.Cart;
import barasanStar.izakayaOrder.model.CartItem;
import barasanStar.izakayaOrder.model.entity.Customer;
import barasanStar.izakayaOrder.model.entity.Menu;
import barasanStar.izakayaOrder.model.entity.Order;
import barasanStar.izakayaOrder.model.entity.OrderItem;
import barasanStar.izakayaOrder.repository.CustomerRepository;
import barasanStar.izakayaOrder.repository.MenuRepository;
import barasanStar.izakayaOrder.repository.OrderItemRepository;
import barasanStar.izakayaOrder.repository.OrderRepository;

@Service
public class OrderService {
	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Autowired
	private MenuRepository menuRepository;

	@Autowired
	private CustomerRepository customerRepository;

	public int calculateCustomerTotalCharge(Long customerId) {
		Integer total = orderItemRepository.calculateTotalAmountByCustomerId(customerId);
		return total == null ? 0 : total;
	}

	public int calculateGroupTotalCharge(Long visitGroupId) {
		Integer total = orderItemRepository.calculateTotalAmountByVisitGroupId(visitGroupId);
		return total == null ? 0 : total;
	}

	public void confirmOrder(Cart cart, Long customerId) {
		if (cart == null || cart.getItems().isEmpty()) {
			throw new IllegalStateException("カートが空です");
		}

		Customer customer = customerRepository.findById(customerId).orElseThrow();

		// 注文作成
		Order order = new Order();
		order.setCustomer(customer);
		order.setStatus(OrderStatus.ORDERED);
		orderRepository.save(order);

		for (CartItem item : cart.getItems()) {
			Menu menu = menuRepository.findById(item.getMenuId()).orElseThrow();
			OrderItem orderItem = new OrderItem();
			orderItem.setOrder(order);
			orderItem.setMenu(menu);
			orderItem.setMenuName(menu.getName());
			orderItem.setPriceWithoutTax(menu.getPriceWithoutTax());
			orderItem.setQuantity(item.getQuantity());
			orderItem.setStatus(OrderItemStatus.ORDERED);
			orderItemRepository.save(orderItem);
		}
	}
}
