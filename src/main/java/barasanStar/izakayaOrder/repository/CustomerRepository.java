package barasanStar.izakayaOrder.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import barasanStar.izakayaOrder.model.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	@Query("""
			    SELECT c.visitGroup.id
			    FROM Customer c
			    WHERE c.customerToken = :customerToken
			      AND c.visitGroup.status = 'ACTIVE'
			""")
	Optional<Long> findActiveVisitGroupIdByCustomerToken(@Param("customerToken") String customerToken);
}
