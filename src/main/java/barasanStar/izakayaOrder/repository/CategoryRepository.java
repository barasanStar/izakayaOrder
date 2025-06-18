package barasanStar.izakayaOrder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import barasanStar.izakayaOrder.model.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
