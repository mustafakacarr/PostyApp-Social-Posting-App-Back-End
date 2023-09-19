package com.tr.mustafakacar.postyApp.config;

import com.tr.mustafakacar.postyApp.security.JwtAuthenticationEntryPoint;
import com.tr.mustafakacar.postyApp.security.JwtAuthenticationFilter;
import com.tr.mustafakacar.postyApp.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;
    private final JwtAuthenticationEntryPoint handler;

    @Autowired
    public SecurityConfig(UserDetailsServiceImpl userDetailsService, JwtAuthenticationEntryPoint handler) {
        this.userDetailsService = userDetailsService;
        this.handler = handler;
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    private static final String[] AUTH_WHITELIST = {      // for Swagger UI v2
            "/v2/api-docs",
            "/swagger-ui.html",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/webjars/**",

            // for Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**",
            "/swagger-ui/**"};

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                                authorizeRequests
                                        .requestMatchers(HttpMethod.GET, "api/v1.0/posts").permitAll()
                                        .requestMatchers(HttpMethod.GET, "/api/v1.0/comments").permitAll()
                                        .requestMatchers("/api/v1.0/auth/**").permitAll()
                                        .requestMatchers(AUTH_WHITELIST).permitAll() //FOR SWAGGER
                                .anyRequest().authenticated()
                )
                .exceptionHandling().authenticationEntryPoint(handler)
                .and()
                .sessionManagement(sessionManagement ->
                        sessionManagement
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .cors()
                .and()
                .csrf().disable();


        return http.build();
    }

}
