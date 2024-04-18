package org.example.lista1techsieciowe.controller.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

/**
 * DTO representing login information.
 */
public class LoginDto {
    @NotBlank
    @Schema(name="username", example = "username")
    private String username;

    @NotBlank
    @Schema(name="password", example = "password")
    private String password;

    /**
     * Constructor for LoginDto.
     * @param username The username.
     * @param password The password.
     */
    public LoginDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Retrieves the username.
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     * @param username The username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Retrieves the password.
     * @return The password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     * @param password The password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
