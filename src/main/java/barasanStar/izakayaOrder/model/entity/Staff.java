package barasanStar.izakayaOrder.model.entity;

import java.time.LocalDateTime;

import barasanStar.izakayaOrder.enums.AccessRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "staffs")
public class Staff {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "名前は必須です")
	@Size(max = 100, message = "名前は100文字以内で入力してください")
	private String name;

	@NotBlank(message = "パスワードは必須です")
	@Size(max = 255)
	private String password;

	@Enumerated(EnumType.STRING)
	private AccessRole access;

	@Column(nullable = false)
	boolean isActive = true;

	@Column(insertable = false, updatable = false)
	private LocalDateTime createdAt;

	@Column(insertable = false, updatable = false)
	private LocalDateTime updatedAt;
}
