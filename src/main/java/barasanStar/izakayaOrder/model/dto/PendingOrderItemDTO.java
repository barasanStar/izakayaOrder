package barasanStar.izakayaOrder.model.dto;

import java.time.LocalDateTime;

import barasanStar.izakayaOrder.enums.OrderItemStatus;

public record PendingOrderItemDTO(
		long id,
		int tableNumber,
		String menuName,
		int quantity,
		LocalDateTime createdAt,
		OrderItemStatus status) {
}
