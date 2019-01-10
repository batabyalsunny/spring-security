package ml.bootcode.springsecurity.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	RestAuthenticationEntryPoint restAuthenticationEntryPoint;

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.security.config.annotation.web.configuration.
	 * WebSecurityConfigurerAdapter#configure(org.springframework.security.config.
	 * annotation.authentication.builders.AuthenticationManagerBuilder)
	 */
	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("admin").password(encoder().encode("admin")).roles("ADMIN").and()
				.withUser("sunny").password(encoder().encode("sunny"));
	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.security.config.annotation.web.configuration.
	 * WebSecurityConfigurerAdapter#configure(org.springframework.security.config.
	 * annotation.web.builders.HttpSecurity)
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()
				.authorizeRequests().antMatchers("/secured/**").authenticated().antMatchers("/admin/**")
				.hasRole("ADMIN").and().formLogin().and().logout();
	}
}
