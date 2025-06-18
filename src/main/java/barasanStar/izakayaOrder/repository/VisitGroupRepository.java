package barasanStar.izakayaOrder.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import barasanStar.izakayaOrder.model.entity.VisitGroup;

public interface VisitGroupRepository extends JpaRepository<VisitGroup, Long> {
	Optional<VisitGroup> findByGroupToken(String groupToken);

	@Query("SELECT vg FROM VisitGroup vg WHERE vg.status = 'ACTIVE'")
	List<VisitGroup> findActiveVisitGroups();

}
