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
/**
 * Provides configuration for Spring Security.
 */
@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final JWTTokenFilter jwtTokenFilter;
    /**
     * Constructs a new SecurityConfig with the provided JWTTokenFilter dependency.
     *
     * @param jwtTokenFilter The JWTTokenFilter instance to be used for JWT token processing.
     */
    public SecurityConfig(JWTTokenFilter jwtTokenFilter) {
        this.jwtTokenFilter = jwtTokenFilter;
    }
    /**
     * Configures the security filter chain for HTTP requests.
     *
     * @param http The HttpSecurity object to configure security settings.
     * @return A SecurityFilterChain instance.
     * @throws Exception If an exception occurs during configuration.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    /**
     * Provides a PasswordEncoder bean for encoding passwords.
     *
     * @return A PasswordEncoder instance.
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Retrieves the AuthenticationManager bean.
     *
     * @param config The AuthenticationConfiguration object.
     * @return An AuthenticationManager instance.
     * @throws Exception If an exception occurs during retrieval.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}