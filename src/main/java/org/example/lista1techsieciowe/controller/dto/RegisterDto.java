package org.example.lista1techsieciowe.controller.dto;

import org.example.lista1techsieciowe.commonTypes.UserRole;

public class RegisterDto {
    private String password;
    private UserRole role;
    private String username;
    private String email;

    public RegisterDto(String password, UserRole role, String username, String email) {
        this.password = password;
        this.role = role;
        this.username = username;
        this.email = email;
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
