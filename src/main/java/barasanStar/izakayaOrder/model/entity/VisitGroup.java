package barasanStar.izakayaOrder.model.entity;

import java.time.LocalDateTime;

import barasanStar.izakayaOrder.enums.GroupTokenStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "visit_groups")
public class VisitGroup {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false)
	private String groupToken;

	@Column(nullable = false)
	private int tableNumber;

	@Column(nullable = false)
	private int numberOfPeople;

	@Enumerated(EnumType.STRING)
	private GroupTokenStatus status;

	private LocalDateTime expiredAt;

	@Column(insertable = false, updatable = false)
	private LocalDateTime createdAt;

	@Column(insertable = false, updatable = false)
	private LocalDateTime updatedAt;

}
