package org.example.lista1techsieciowe.controller.dto.user;

public class CreateUserDto {
    private String username;
    private String email;
    private String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CreateUserDto(String username, String email, String name) {
        this.username = username;
        this.email = email;
        this.name = name;

    }
}
