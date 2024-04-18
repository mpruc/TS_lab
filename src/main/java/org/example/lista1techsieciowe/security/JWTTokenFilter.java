package org.example.lista1techsieciowe.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.lista1techsieciowe.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
/**
 * Class that represents a filter for processing JWT tokens in the HTTP requests.
 * It extends OncePerRequestFilter to ensure it's executed only once per request.
 */
@Component
public class JWTTokenFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    /**
     * Constructs a new JWTTokenFilter with the provided JwtService dependency.
     * @param jwtService The JwtService instance to use for token-related operations.
     */
    @Autowired
    public JWTTokenFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    /**
     * Filters the HTTP request to process JWT tokens.
     *
     * @param request     The HTTP servlet request.
     * @param response    The HTTP servlet response.
     * @param filterChain The filter chain for invoking subsequent filters.
     * @throws ServletException If an exception occurs during servlet processing.
     * @throws IOException      If an I/O exception occurs.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
            final String jwt;

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }

            jwt = authHeader.substring(7);
            final String username = jwtService.extractUsername(jwt);
            final String role = jwtService.extractRole(jwt).name();

            if (username != null && !username.isEmpty() && SecurityContextHolder.getContext().getAuthentication() == null) {
                if (jwtService.isTokenValid(jwt)) {
                    SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, List.of(new SimpleGrantedAuthority(role)));
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    securityContext.setAuthentication(authenticationToken);
                    SecurityContextHolder.setContext(securityContext);
                }
            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            filterChain.doFilter(request, response);
        }

    }
}
