package kr.co.myproject.security;

import java.io.IOException;
import java.util.Arrays;
import jakarta.servlet.DispatcherType;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Configuration
@EnableWebSecurity
public class SecutiryConfig {
	 
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		 http
		 
		 .exceptionHandling(exceptionHandling -> exceptionHandling.accessDeniedPage("/login-page")) // 403번대 에러는 자동으로 페이지 이동
		 .authorizeHttpRequests(authz -> authz
				 .dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.INCLUDE, DispatcherType.ERROR, DispatcherType.REQUEST).permitAll()
				 .requestMatchers("/css/**", "/js/**", "find-password-page", "find-password").permitAll()
				 .requestMatchers("/WEB-INF/views/**").denyAll()
				 .requestMatchers("/", "/login-page", "/register-page", "/board-check-page/**", "/board-list-page").permitAll()
				 .requestMatchers( "/login", "/register").permitAll()
				 .requestMatchers("/logout").hasAnyAuthority("ADMIN","MANAGER","MEMBER")
				 .requestMatchers("/notice-check-page/**").hasAnyAuthority("ADMIN","MANAGER","MEMBER")
				 .requestMatchers("/notice-add-page", "/notice-modify-page/**").hasAnyAuthority("ADMIN","MANAGER")

				 .requestMatchers(HttpMethod.GET, "/my-page").hasAnyAuthority("ADMIN","MANAGER","MEMBER")
				 .requestMatchers(HttpMethod.GET, "/board-add-page").hasAnyAuthority("ADMIN","MANAGER","MEMBER")
				 .requestMatchers(HttpMethod.GET, "/board-modify-page/**").hasAnyAuthority("ADMIN","MANAGER")
				 .requestMatchers(HttpMethod.POST, "/board-add", "/board-modify", "/board-delete", "/comment-add").hasAnyAuthority("ADMIN","MANAGER")

				 .anyRequest().authenticated())
		 .csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
		 .cors(cors -> cors.configurationSource(corsConfigurationSource()))
		 .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
		 .formLogin(login -> login
		 .loginPage("/login-page")
		 .loginProcessingUrl("/login")
		 .failureUrl("/login-page?error=true")
		 .usernameParameter("username")
		 .passwordParameter("password")
		 .successHandler(authenticationSuccessHandler())
		 .permitAll()
		 )
		 .logout(logout -> logout
				 .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				 .logoutSuccessUrl("/")
				 .invalidateHttpSession(true)
				 .deleteCookies("JSESSIONID")
				 .permitAll()
				 );
		 
		 return http.build();
	}
	
	

	@Bean
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
		
		return new SimpleUrlAuthenticationSuccessHandler() {
			@Override
			public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
					Authentication authentication) throws IOException, ServletException {
				
				HttpSession session = request.getSession();
				boolean isManager = authentication.getAuthorities().stream().anyMatch(grantedAuthority -> 
				grantedAuthority.getAuthority().equals("ADMIN") || grantedAuthority.getAuthority().equals("MANAGER"));
			
				if(isManager)
				{
					session.setAttribute("ADMIN", true);
					session.setAttribute("MANAGER", true);
				}
				else
				{
					session.setAttribute("MEMBER", true);
				}
				
				session.setAttribute("username", authentication.getName());
				session.setAttribute("isAuthenticated", true);
				
				response.sendRedirect(request.getContextPath() + "/");
				
				super.onAuthenticationSuccess(request, response, authentication);
			}
		};
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:8081", "https://localhost:8081"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
		
		org.springframework.web.cors.UrlBasedCorsConfigurationSource source = new org.springframework.web.cors.UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfiguration);
		return source;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
}
