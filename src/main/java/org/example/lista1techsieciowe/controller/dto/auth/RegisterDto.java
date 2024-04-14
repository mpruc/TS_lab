package org.example.lista1techsieciowe.controller.dto.auth;

import jakarta.validation.constraints.NotBlank;
import org.example.lista1techsieciowe.commonTypes.UserRole;

public class RegisterDto {
    @NotBlank
    private String password;
    @NotBlank
    private UserRole role;
    @NotBlank
    private String username;
    @NotBlank
    private String name;
    @NotBlank
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
