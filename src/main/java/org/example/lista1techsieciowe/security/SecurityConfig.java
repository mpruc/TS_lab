package org.example.lista1techsieciowe.security;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

//    @Value("${jwt.token.key}")
//    private String key;
    private final JWTTokenFilter jwtTokenFilter;

    public SecurityConfig(JWTTokenFilter jwtTokenFilter) {

        this.jwtTokenFilter = jwtTokenFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http
//                .csrf(AbstractHttpConfigurer::disable)
//                .sessionManagement(httpSecuritySessionManagementConfigurer ->
//                        httpSecuritySessionManagementConfigurer
//                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
////                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
////                        authorizationManagerRequestMatcherRegistry
////                                .requestMatchers(HttpMethod.POST, "/login").permitAll()
////                                .requestMatchers(HttpMethod.POST, "/register").hasRole("ADMIN")
////                )
//                .build();
//    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}