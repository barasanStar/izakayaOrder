package barasanStar.izakayaOrder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import barasanStar.izakayaOrder.enums.OrderItemStatus;
import barasanStar.izakayaOrder.model.entity.OrderItem;
import barasanStar.izakayaOrder.repository.OrderItemRepository;
import jakarta.transaction.Transactional;

@Service
public class OrderItemService {
	@Autowired
	private OrderItemRepository orderItemRepository;

	@Transactional
	public void updateStatus(Long orderItemId, OrderItemStatus status) {
		OrderItem item = orderItemRepository.findById(orderItemId)
				.orElseThrow(() -> new IllegalArgumentException("指定された注文明細が見つかりません: " + orderItemId));
		item.setStatus(status);
		orderItemRepository.save(item);
	}

}
