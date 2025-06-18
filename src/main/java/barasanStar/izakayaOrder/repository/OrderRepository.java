package barasanStar.izakayaOrder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import barasanStar.izakayaOrder.model.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
