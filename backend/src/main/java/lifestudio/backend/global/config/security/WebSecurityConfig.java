package lifestudio.backend.global.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private final JwtTokenProvider jwtTokenProvider;

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// authenticationManager를 Bean 등록합니다.
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
				.cors().and()
			.httpBasic().disable() // 기본 설정 해제
			.sessionManagement() // JWT 인증방식을 사용하기 때문에 세선  해제
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
			.authorizeRequests()// 요청에 대한 사용권한 체크
				// .antMatchers(HttpMethod.POST,"/api/auth/**").permitAll()
				// .antMatchers(HttpMethod.GET,"/api/users/**","/api/photos/**","/api/studios/**").permitAll()
				// .antMatchers(HttpMethod.PUT,"/api/users/**","/api/photos/**","/api/studios/**").hasRole("USER")
				// .antMatchers("/api/reviews/**", "/api/likes/**").hasRole("USER")
				// .anyRequest().hasRole("ADMIN") // 그외 나머지 요청은 관리자만 가능
				.anyRequest().permitAll() // 일단 개발용으로 모든 권한 허용~
				.and()
			.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
				UsernamePasswordAuthenticationFilter.class)
			.addFilterBefore(new CorsFilter(), JwtAuthenticationFilter.class);

		// JwtAuthenticationFilter를 UsernamePasswordAuthenticationFilter 전에 넣는다
	}

}
