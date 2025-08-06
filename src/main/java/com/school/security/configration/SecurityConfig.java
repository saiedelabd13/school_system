package com.school.security.configration;


import com.school.security.AuthFilter;
import com.school.security.JwtUnAuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {


    String[] PUBLIC_END_POINTS = {"/api/v1/auth/login", "/api/v1/auth/refresh-token", "/api/v1/auth/logout"};
    @Autowired
    @Lazy
    private UserDetailsService userDetailsService;
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUnAuthResponse jwtUnAuthResponse;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Remove the configure method

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/api/v1/auth/login", "/home", "/public/**").permitAll()
                        .requestMatchers(PUBLIC_END_POINTS).permitAll()
                        .anyRequest().authenticated()

                );
        return http.build();
    }

    @Bean
    public AuthFilter authFilter() {
        return new AuthFilter();
    }

}
