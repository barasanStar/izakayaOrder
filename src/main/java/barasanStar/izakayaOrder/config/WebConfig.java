package barasanStar.izakayaOrder.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Autowired
	private CustomerTokenInterceptor customerTokenInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(customerTokenInterceptor)
				.addPathPatterns("/**") // 全画面に適用
				.excludePathPatterns("/staff/**", "/static/**", "/css/**", "/js/**"); // 静的リソース除外
	}
}
