package com.example.demo.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails user = User.builder().username("user").password(passwordEncoder().encode("password")).roles("USER").build();

        UserDetails admin = User.builder().username("admin").password(passwordEncoder().encode("password")).roles("USER", "ADMIN").build();

        return new InMemoryUserDetailsManager(user,admin);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(authz-> authz
        .requestMatchers(HttpMethod.GET,"/authors/**","/greetings/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
        .requestMatchers(HttpMethod.POST,"/authors","/greetings").hasRole("USER")
        .requestMatchers(HttpMethod.PUT,"/authors/**","/greetings/**").hasRole("USER")
        .requestMatchers(HttpMethod.DELETE,"/authors/**","/greetings/**").hasRole("ADMIN")
        .anyRequest().authenticated()
        )
        .httpBasic(withDefaults());
        return http.build();
    }
}
