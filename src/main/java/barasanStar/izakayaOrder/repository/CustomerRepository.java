package barasanStar.izakayaOrder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import barasanStar.izakayaOrder.model.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
