package org.example.lista1techsieciowe.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import org.example.lista1techsieciowe.controller.dto.auth.LoginDto;
import org.example.lista1techsieciowe.controller.dto.auth.LoginResponseDto;
import org.example.lista1techsieciowe.controller.dto.auth.RegisterDto;
import org.example.lista1techsieciowe.controller.dto.auth.RegisterResponseDto;
import org.example.lista1techsieciowe.service.auth.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")

public class LoginController {
    private final LoginService loginService;
    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/register")
    @PreAuthorize("hasRole('LIBRARIAN')")
    @ApiResponse(responseCode = "201")
    public ResponseEntity<RegisterResponseDto> register(@RequestBody RegisterDto requestbody){
        RegisterResponseDto dto = loginService.register(requestbody);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);

}
    @PostMapping("/log")
    @PreAuthorize("permitAll()")
    @SecurityRequirements
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Login succeeded" ),
            @ApiResponse(responseCode = "404", description = "Login failed", content = @Content)

    })
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto requestBody) {
        LoginResponseDto dto = loginService.log(requestBody);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);

    }
    @DeleteMapping("/{username}")
    @PreAuthorize("hasRole('LIBRARIAN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted successfully" ),
            @ApiResponse(responseCode = "404", description = "Delete failed", content = @Content),
            @ApiResponse(responseCode = "403", description = "Not authorized", content = @Content)
    })
    public void delete(@PathVariable String username) {
        loginService.DeleteByUsername(username);
    }

}



