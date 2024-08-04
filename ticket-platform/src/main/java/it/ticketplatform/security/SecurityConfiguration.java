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
       //.requestMatchers("/lista-admin", "/show/**", "/insert-nota/**", "/ticket-search", "/update-ticket/**", "/insert-ticket", "/delete/**").hasAuthority("ADMIN")
       //.requestMatchers("/show/**", "/insert-nota/**", "/update-state/**", "/update-user/**", "/user-page/**").hasAuthority("USER")
       .requestMatchers("/**").permitAll()   
       .and().formLogin()
       .defaultSuccessUrl("/lista-admin", true)
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