package barasanStar.izakayaOrder.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class CategoriesControllerTest {

	@Autowired
	private MockMvc mockMvc;

	// 有効な固定のグループトークンを渡す
	@Test
	void testGetMenus() throws Exception {
		mockMvc.perform(get("/categories")
				.param("groupToken", "eba55700-8b86-4ad9-9242-d57eb6de390b"))
				.andExpect(status().isOk())
				.andExpect(content().contentType("text/html;charset=UTF-8"));
	}
}
