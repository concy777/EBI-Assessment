package com.ebi.assessment.security;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("root").password("{noop}root").roles("USER", "ADMIN").and()
				.withUser("ebi").password("{noop}ebi123").roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().and().authorizeRequests().antMatchers(HttpMethod.GET, "/persons/**").hasRole("USER")
				.antMatchers(HttpMethod.POST, "/persons").hasRole("ADMIN").antMatchers(HttpMethod.PUT, "/persons/**")
				.hasRole("ADMIN").antMatchers(HttpMethod.PATCH, "/persons/**").hasRole("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/persons/**").hasRole("ADMIN").and().csrf().disable().formLogin()
				.disable();
	}
}
