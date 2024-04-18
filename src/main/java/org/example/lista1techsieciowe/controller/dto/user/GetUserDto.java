package org.example.lista1techsieciowe.controller.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Data Transfer Object (DTO) representing a user.
 */
public class GetUserDto {

    @Schema(name = "userId", example = "1")
    private Integer id;

    @Schema(name = "name", example = "name")
    private String name;

    @Schema(name = "email", example = "example@email.com")
    private String email;

    /**
     * Constructs a new GetUserDto with the specified parameters.
     *
     * @param id The ID of the user.
     * @param name The name of the user.
     * @param email The email of the user.
     */
    public GetUserDto(Integer id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    /**
     * Retrieves the ID of the user.
     * @return The user ID.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the ID of the user.
     * @param id The user ID.
     */
    public void setId(Integer id) {
        this.id = id;
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
}
