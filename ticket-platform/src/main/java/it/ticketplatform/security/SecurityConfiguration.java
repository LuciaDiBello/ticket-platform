package it.ticketplatform.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

	 @Bean
	  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	   http.cors()
	      .and()
	      .authorizeHttpRequests()
	      //.requestMatchers("/index", "/lista-admin", "/show/**", "/insert-nota/**", "/ticket-search", "/update-ticket/**", "/insert-ticket", "/delete/**", "/user-page/1", "/update-user/1" ).hasAuthority("ADMIN")
	      //.requestMatchers("/index","/show/**", "/insert-nota/**", "/update-state/**", "/update-user/**", "/user-page/**").hasAuthority("USER")
	       .requestMatchers("/**").permitAll()   
	       .and().formLogin()
	       .defaultSuccessUrl("/index", true)
	       .and().logout()
	       .and().exceptionHandling()
	       .and().csrf().disable();
	     return http.build();
	    }

    @Bean
    DatabaseUserDetailsService userDetailsService() {
        return new DatabaseUserDetailsService();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }   
}