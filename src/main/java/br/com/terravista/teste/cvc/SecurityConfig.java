package br.com.terravista.teste.cvc;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("*/h2/**", 
								   "*/oauth/**", 
								   "*/swagger-ui.html",
								   "*/configuration/**",
								   "*/swagger-resources/**",
								   "*/configuration/**",
								   "*/webjars/**",
								   "*/v2/**");
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
		.authorizeRequests()
		.antMatchers(HttpMethod.TRACE, "/**").denyAll()
		.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
		.antMatchers("/admin/**").authenticated()
		.anyRequest().permitAll()
		.and().httpBasic()
		.and().headers().frameOptions().disable()
		.and().csrf().disable();
	}


}
