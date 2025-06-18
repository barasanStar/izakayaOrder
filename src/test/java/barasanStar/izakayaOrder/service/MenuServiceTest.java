package barasanStar.izakayaOrder.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import barasanStar.izakayaOrder.model.entity.Menu;
import barasanStar.izakayaOrder.repository.MenuRepository;

@ExtendWith(MockitoExtension.class)
public class MenuServiceTest {
	@Mock
	private MenuRepository menuRepository;

	@InjectMocks
	private MenuService menuService;

	@Test
	void testFindAvailableMenus() {
		List<Menu> mockMenus = List.of(new Menu("カレー", 800, true));
		when(menuRepository.findByIsAvailableTrue()).thenReturn(mockMenus);

		List<Menu> result = menuService.findAvailableMenus();

		assertEquals(1, result.size());
		assertEquals("カレー", result.get(0).getName());
	}
}
