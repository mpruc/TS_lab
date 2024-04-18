package org.example.lista1techsieciowe.controller.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

/**
 * Data Transfer Object (DTO) representing the creation of a user.
 */
public class CreateUserDto {

    @Schema(name = "username", example = "username")
    private String username;

    @Schema(name = "email", example = "example@email.com")
    private String email;

    @Schema(name = "name", example ="name")
    private String name;

    /**
     * Constructs a new CreateUserDto with the specified parameters.
     *
     * @param username The username of the user.
     * @param email The email of the user.
     * @param name The name of the user.
     */
    public CreateUserDto(String username, String email, String name) {
        this.username = username;
        this.email = email;
        this.name = name;
    }

    /**
     * Retrieves the username of the user.
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user.
     * @param username The username.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Retrieves the email of the user.
     * @return The email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user.
     * @param email The email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieves the name of the user.
     * @return The name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the user.
     * @param name The name.
     */
    public void setName(String name) {
        this.name = name;
    }
}
