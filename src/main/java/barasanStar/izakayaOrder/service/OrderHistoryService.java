package barasanStar.izakayaOrder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import barasanStar.izakayaOrder.model.dto.OrderItemSummaryDTO;
import barasanStar.izakayaOrder.repository.OrderItemRepository;

@Service
public class OrderHistoryService {

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Autowired
	private OrderService orderService;

	public OrderHistoryData getOrderHistory(Long customerId, Long visitGroupId) {
		List<OrderItemSummaryDTO> summaries = orderItemRepository.findSummaryByCustomerId(customerId);
		int customerTotal = orderService.calculateCustomerTotalCharge(customerId);
		int groupTotal = orderService.calculateGroupTotalCharge(visitGroupId);
		return new OrderHistoryData(summaries, customerTotal, groupTotal);
	}

	public static class OrderHistoryData {
		private final List<OrderItemSummaryDTO> summaries;
		private final int customerTotalCharge;
		private final int groupTotalCharge;

		public OrderHistoryData(
				List<OrderItemSummaryDTO> summaries,
				int customerTotalCharge,
				int groupTotalCharge) {
			this.summaries = summaries;
			this.customerTotalCharge = customerTotalCharge;
			this.groupTotalCharge = groupTotalCharge;
		}

		public List<OrderItemSummaryDTO> getSummaries() {
			return summaries;
		}

		public int getCustomerTotalCharge() {
			return customerTotalCharge;
		}

		public int getGroupTotalCharge() {
			return groupTotalCharge;
		}
	}
}
