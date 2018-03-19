package com.uniovi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	
	/**
	 * Inyecta UserDetailsService
	 */
	@Autowired
	private UserDetailsService userDetailsService;


	@Configuration
	@Order(1)
	public class UserLoginConfiguration extends WebSecurityConfigurerAdapter {
		@Autowired
		private UserDetailsService userDetailsService;

		@Bean
		public BCryptPasswordEncoder bCryptPasswordEncoder() {
			return new BCryptPasswordEncoder();
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.requestMatcher(new AntPathRequestMatcher("/login")).csrf().disable().authorizeRequests()
					.antMatchers("/css/**", "/img/**", "/script/**", "/", "/index", "/signup", "/login").permitAll()
					.antMatchers("/admin/**").hasAnyAuthority("ROLE_ADMIN")
					.anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll()
					.defaultSuccessUrl("/user/list").failureUrl("/login?error=true").and().logout().permitAll()
					.logoutSuccessUrl("/");
		}

		@Autowired
		public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
		}
	}

	@Configuration
	@Order(2)
	public class AdministradorLoginConfiguration extends WebSecurityConfigurerAdapter {
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable().authorizeRequests()
					.antMatchers("/css/**", "/img/**", "/script/**", "/", "/index", "/signup", "/login").permitAll()
					.antMatchers("/admin/**").hasAnyAuthority("ROLE_ADMIN")
					.anyRequest().authenticated().and().formLogin().loginPage("/admin/login").permitAll()
					.defaultSuccessUrl("/admin/list").failureUrl("/admin/login?error=true").and().logout().permitAll()
					.logoutSuccessUrl("/");
		}
	}

}
