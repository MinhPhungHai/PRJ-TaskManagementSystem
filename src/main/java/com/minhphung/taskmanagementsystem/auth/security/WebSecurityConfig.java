package com.minhphung.taskmanagementsystem.auth.security;

import com.minhphung.taskmanagementsystem.auth.security.jwt.AuthEntryPointJwt;
import com.minhphung.taskmanagementsystem.auth.security.jwt.AuthTokenFilter;
import com.minhphung.taskmanagementsystem.auth.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class WebSecurityConfig {
    // Inject custom user details service for authentication
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    // Inject handler that deals with unauthorized access (401 errors)
    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    // Define a filter that intercepts requests to check JWT token
    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter(){
        return new AuthTokenFilter();
    }

    // Define a password encoder
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // Authentication provider uses our userDetailsService and encoder
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    // AuthenticationManager is needed for login/authentication flow
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    // Main security configuration: define which endpoints require auth
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // disable CSRF (common in APIs)
                .exceptionHandling(ex -> ex.authenticationEntryPoint(unauthorizedHandler)) // handle 401
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // no session, stateless API
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll() // allow unauthenticated access to auth routes
                        .requestMatchers("/api/test/**").permitAll() // allow test routes
                        .requestMatchers("/error").permitAll()
                        .anyRequest().authenticated() // all other requests must be authenticated
                );

        // Add our custom AuthTokenFilter before Spring's UsernamePasswordAuthenticationFilter
        http.authenticationProvider(authenticationProvider());
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
