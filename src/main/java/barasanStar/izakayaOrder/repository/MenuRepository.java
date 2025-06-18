package barasanStar.izakayaOrder.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import barasanStar.izakayaOrder.model.entity.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long> {
	List<Menu> findByCategoryId(Long categoryId);

	// SELECT * FROM menu WHERE is_available = true
	List<Menu> findByIsAvailableTrue();
}
