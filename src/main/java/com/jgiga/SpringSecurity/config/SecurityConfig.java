package com.jgiga.SpringSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // Customizer<CsrfConfigurer<HttpSecurity>> custCsrf = new
        // Customizer<CsrfConfigurer<HttpSecurity>>() {
        // @Override
        // public void customize(CsrfConfigurer<HttpSecurity> customizer) {
        // customizer.disable();
        // }
        // };
        // same as
        // .csrf(customizer -> customizer.disable())
        http
                .csrf(customizer -> customizer.disable())
                .authorizeHttpRequests(request -> request.anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // http.formLogin(Customizer.withDefaults());

        // // For postman
        // http.httpBasic(Customizer.withDefaults());

        return http.build();
    }

    // Can not user withDefaultPasswordEncoder() DEPRECATED instead we use this
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {

        UserDetails user01 = User.builder()
                .username("g__john")
                .password(passwordEncoder.encode("papu1234"))
                .roles("USER")
                .build();

        UserDetails user02 = User.builder()
                .username("gjohn")
                .password(passwordEncoder.encode("papu1234"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user01, user02);
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        return provider;

    }

}
