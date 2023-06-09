package com.examen.web.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new CustomPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(authenticationProvider());
    }


    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }


    @Bean
    public JWTAuthenticationFilter jwtAuthenticationFilter() {
        JWTAuthenticationFilter filter = new JWTAuthenticationFilter();
        filter.setAuthenticationManager(authenticationManager());
        return filter;
    }

    @Bean
    public JWTAuthorizationTokenFilter jwtAuthorizationTokenFilter() {
        return new JWTAuthorizationTokenFilter(authenticationManager(), userDetailsService());
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .headers(a -> a.frameOptions().sameOrigin())
                .exceptionHandling(a -> a.authenticationEntryPoint(new JWTAuthenticationEntryPoint()))

                .sessionManagement(a -> a.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeRequests(a ->
                        a.antMatchers(HttpMethod.GET, "/", "/*.html", "/favicon.ico", "/**/*.html", "/**/*.css", "/**/*.js", "/**/*.json", "/**/*.svg").permitAll()
                                .antMatchers(HttpMethod.GET, "/", "/passport/**","/cuenta/**","/apidoc/**","/transaccion/**", "/dashboard/**", "/sys/**").permitAll()
                                .antMatchers(HttpMethod.POST, "/login").permitAll()
                                .antMatchers( "/v1/**").permitAll()
                                .antMatchers( "/v3/api-docs/**").permitAll()
                                .anyRequest().authenticated())
                .addFilter(jwtAuthenticationFilter())
                .addFilter(jwtAuthorizationTokenFilter())
        ;
        return http.build();
    }


}
