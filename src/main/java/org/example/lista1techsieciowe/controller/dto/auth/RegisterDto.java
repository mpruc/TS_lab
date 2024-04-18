package org.example.lista1techsieciowe.controller.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import org.example.lista1techsieciowe.commonTypes.UserRole;

/**
 * DTO representing registration information.
 */
public class RegisterDto {
    @NotBlank(message = "Password is required")
    @Schema(name = "password", example = "password")
    private String password;

    @NotBlank(message = "Role is required")
    @Schema(name = "role", example = "ROLE_LIBRARIAN")
    private UserRole role;

    @NotBlank(message = "Username is required")
    @Schema(name = "username", example = "username")
    private String username;

    @NotBlank(message = "Name is required")
    @Schema(name = "name", example ="name")
    private String name;

    @NotBlank(message = "Email is required")
    @Schema(name = "email", example = "example@email.com")
    private String email;

    /**
     * Constructor for RegisterDto.
     * @param password The user's password.
     * @param role The user's role.
     * @param username The user's username.
     * @param name The user's name.
     * @param email The user's email.
     */
    public RegisterDto(String password, UserRole role, String username, String name, String email) {
        this.password = password;
        this.role = role;
        this.username = username;
        this.name = name;
        this.email = email;
    }

    /**
     * Retrieves the user's name.
     * @return The user's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the user's name.
     * @param name The user's name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the user's password.
     * @return The user's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's password.
     * @param password The user's password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Retrieves the user's role.
     * @return The user's role.
     */
    public UserRole getRole() {
        return role;
    }

    /**
     * Sets the user's role.
     * @param role The user's role to set.
     */
    public void setRole(UserRole role) {
        this.role = role;
    }

    /**
     * Retrieves the user's username.
     * @return The user's username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the user's username.
     * @param username The user's username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Retrieves the user's email.
     * @return The user's email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email.
     * @param email The user's email to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
