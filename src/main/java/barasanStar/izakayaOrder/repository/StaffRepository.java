package barasanStar.izakayaOrder.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import barasanStar.izakayaOrder.model.entity.Staff;

public interface StaffRepository extends JpaRepository<Staff, Long> {
	Optional<Staff> findByName(String name);
}
