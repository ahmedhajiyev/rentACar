package kodlama.io.rentACar.core.utilities.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import kodlama.io.rentACar.entities.concretes.Authority;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {

	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final AuthenticationProvider authenticationProvider;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http 
		 		.csrf()
		 		.disable()
		 		.authorizeHttpRequests()
		 		.requestMatchers("/api/auth/**")
		 		.permitAll()
//		 		.requestMatchers("/api/models/**")
//		 		.hasAuthority(Authority.ADMIN.getAuthority().toUpperCase(Locale.ENGLISH))
		 		.anyRequest()
		 		.authenticated()
		 		.and()
		 		.sessionManagement()
		 		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		 		.and()
		 		.authenticationProvider(authenticationProvider)
		 		.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
}