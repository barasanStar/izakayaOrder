package barasanStar.izakayaOrder.model.dto;

import java.time.LocalDateTime;

import barasanStar.izakayaOrder.enums.OrderItemStatus;

public record OrderItemSummaryDTO(
		String menuName,
		int priceWithoutTax,
		int quantity,
		OrderItemStatus status,
		LocalDateTime createdAt) {
}
