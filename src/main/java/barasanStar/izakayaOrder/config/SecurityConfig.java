package barasanStar.izakayaOrder.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); // BCrypt 推奨
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.formLogin(form -> form
						.loginPage("/staff/login")
						.loginProcessingUrl("/staff/login")
						.defaultSuccessUrl("/staff/home", true)
						.failureUrl("/staff/login?error")
						.permitAll())
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/staff/login").permitAll()
						.requestMatchers("/categories/**", "/categories").permitAll()
						.requestMatchers("/staff/**").hasAnyAuthority("STAFF", "SENIOR")
						.anyRequest().permitAll())
				.csrf(Customizer.withDefaults())
				.exceptionHandling(ex -> ex
						.accessDeniedPage("/access-denied"));

		//		.anyRequest().denyAll());

		return http.build();
	}
}
