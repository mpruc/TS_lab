package org.example.lista1techsieciowe.controller.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import org.example.lista1techsieciowe.commonTypes.UserRole;

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
    @NotBlank(message = "Name is required" )
    @Schema(name = "name", example ="name")
    private String name;
    @NotBlank(message = "email is required" )
    @Schema(name = "email", example = "example@email.com")
    private String email;

    public RegisterDto(String password, UserRole role, String username, String name, String email) {
        this.password = password;
        this.role = role;
        this.username = username;
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
