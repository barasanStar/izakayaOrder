package barasanStar.izakayaOrder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import barasanStar.izakayaOrder.model.entity.Menu;
import barasanStar.izakayaOrder.repository.MenuRepository;

@Service
public class MenuService {
	@Autowired
	private MenuRepository menuRepository;

	public List<Menu> findByCategoryId(Long categoryId) {
		return menuRepository.findByCategoryId(categoryId);
	}

	public Menu findById(Long menuId) {
		return menuRepository.findById(menuId)
				.orElseThrow(() -> new RuntimeException("メニューが見つかりません"));
	}

	public List<Menu> findAvailableMenus() {
		return menuRepository.findByIsAvailableTrue();
	}

	//	public List<Menu> findAll() {
	//		return repository.findAll();
	//	}

}
