package org.example.lista1techsieciowe.controller.dto.user;

/**
 * Data Transfer Object (DTO) representing the response for creating a user.
 */
public class CreateUserResponseDto {

    private Integer userId;
    private String username;
    private String email;
    private String name;

    /**
     * Constructs a new CreateUserResponseDto with the specified parameters.
     *
     * @param userId The ID of the created user.
     * @param username The username of the created user.
     * @param email The email of the created user.
     * @param name The name of the created user.
     */
    public CreateUserResponseDto(Integer userId, String username, String email, String name) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.name = name;
    }

    /**
     * Retrieves the ID of the created user.
     * @return The user ID.
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * Sets the ID of the created user.
     * @param userId The user ID.
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * Retrieves the username of the created user.
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the created user.
     * @param username The username.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Retrieves the email of the created user.
     * @return The email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the created user.
     * @param email The email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieves the name of the created user.
     * @return The name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the created user.
     * @param name The name.
     */
    public void setName(String name) {
        this.name = name;
    }
}
