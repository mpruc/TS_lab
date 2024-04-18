package org.example.lista1techsieciowe.controller.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import org.example.lista1techsieciowe.commonTypes.UserRole;

/**
 * DTO representing a response to a registration request.
 */
public class RegisterResponseDto {
    @Schema(name="userId", example = "1")
    private Integer userId;

    @Schema(name= "username", example = "username")
    private String username;

    @Schema(name = "role", example = "ROLE_READER")
    private UserRole role;

    /**
     * Constructor for RegisterResponseDto.
     * @param username The registered username.
     * @param role The role assigned to the user.
     * @param userId The ID of the registered user.
     */
    public RegisterResponseDto(String username, UserRole role, Integer userId) {
        this.username = username;
        this.role = role;
        this.userId = userId;
    }

    /**
     * Retrieves the ID of the registered user.
     * @return The ID of the registered user.
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * Sets the ID of the registered user.
     * @param userId The ID of the registered user to set.
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * Retrieves the registered username.
     * @return The registered username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the registered username.
     * @param username The registered username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Retrieves the role assigned to the user.
     * @return The role assigned to the user.
     */
    public UserRole getRole() {
        return role;
    }

    /**
     * Sets the role assigned to the user.
     * @param role The role assigned to the user to set.
     */
    public void setRole(UserRole role) {
        this.role = role;
    }
}
