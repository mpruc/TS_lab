package org.example.lista1techsieciowe.controller.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;

public class GetUserDto {
    @Schema(name = "userId", example = "1")
    private Integer id;
    @Schema(name = "name", example = "name")
    private String name;
    @Schema(name = "email", example = "example@email.com")
    private String email;

    public GetUserDto(Integer id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
