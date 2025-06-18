package barasanStar.izakayaOrder.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import barasanStar.izakayaOrder.model.dto.OrderItemSummaryDTO;
import barasanStar.izakayaOrder.model.dto.PendingOrderItemDTO;
import barasanStar.izakayaOrder.model.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
	@Query("""
				SELECT new barasanStar.izakayaOrder.model.dto.OrderItemSummaryDTO(
					i.menuName,
					i.priceWithoutTax,
					i.quantity,
					i.status,
					i.createdAt
				)
				FROM OrderItem i
				JOIN i.order o
				ON o.customer.id = :customerId
			""")
	List<OrderItemSummaryDTO> findSummaryByCustomerId(@Param("customerId") Long customerId);

	@Query("""
			    SELECT new barasanStar.izakayaOrder.model.dto.PendingOrderItemDTO(
			    	i.id,
			        c.visitGroup.tableNumber,
			        i.menuName,
			        i.quantity,
			        i.createdAt,
			        i.status
			    )
			    FROM OrderItem i
			    JOIN i.order o
			    JOIN o.customer c
			    WHERE i.status IN('ORDERED', 'PREPARING')
			    ORDER BY o.createdAt ASC
			""")
	List<PendingOrderItemDTO> findUnservedItems();

	@Query("""
			    SELECT SUM(i.priceWithoutTax * i.quantity)
			    FROM OrderItem i
			    JOIN i.order o
			    JOIN o.customer c
			    WHERE c.visitGroup.id = :visitGroupId
			      AND i.status != 'CANCELLED'
			""")
	Integer calculateTotalAmountByVisitGroupId(@Param("visitGroupId") Long visitGroupId);

	@Query("""
			    SELECT SUM(i.priceWithoutTax * i.quantity)
			    FROM OrderItem i
			    JOIN i.order o
			    JOIN o.customer c
			    WHERE c.id = :customerId
			      AND i.status != 'CANCELLED'
			""")
	Integer calculateTotalAmountByCustomerId(@Param("customerId") Long customerId);

}
