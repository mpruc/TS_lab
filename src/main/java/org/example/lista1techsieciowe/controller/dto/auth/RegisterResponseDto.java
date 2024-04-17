package org.example.lista1techsieciowe.controller.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import org.example.lista1techsieciowe.commonTypes.UserRole;

public class RegisterResponseDto {
    @Schema(name="userId", example = "1")
    private Integer userId;
    @Schema(name= "username", example = "username")
    private String username;
    @Schema(name = "role", example = "ROLE_READER")
    private UserRole role;

    public RegisterResponseDto(String username, UserRole role, Integer userId) {
        this.username = username;
        this.role = role;
        this.userId = userId;
    }
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
