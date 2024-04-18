package org.example.lista1techsieciowe.controller.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO representing a response to a login request.
 */
public class LoginResponseDto {
    @Schema(name = "token", example = "token")
    private String token;

    /**
     * Constructor for LoginResponseDto.
     * @param token The authentication token.
     */
    public LoginResponseDto(String token) {
        this.token = token;
    }

    /**
     * Retrieves the authentication token.
     * @return The authentication token.
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets the authentication token.
     * @param token The authentication token to set.
     */
    public void setToken(String token) {
        this.token = token;
    }
}
