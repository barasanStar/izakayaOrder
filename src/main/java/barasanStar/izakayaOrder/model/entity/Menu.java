package barasanStar.izakayaOrder.model.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "menus")
public class Menu {
	public Menu() {
	}

	public Menu(String name, int priceWithoutTax, boolean isAvailable) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.name = name;
		this.priceWithoutTax = priceWithoutTax;
		this.isAvailable = isAvailable;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private int priceWithoutTax;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	private String image_url;
	private boolean isAvailable;

	@Column(insertable = false, updatable = false)
	private LocalDateTime createdAt;

	@Column(insertable = false, updatable = false)
	private LocalDateTime updatedAt;
}
