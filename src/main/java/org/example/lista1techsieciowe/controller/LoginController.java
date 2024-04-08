package org.example.lista1techsieciowe.controller;

import org.example.lista1techsieciowe.controller.dto.LoginDto;
import org.example.lista1techsieciowe.controller.dto.LoginResponseDto;
import org.example.lista1techsieciowe.controller.dto.RegisterDto;
import org.example.lista1techsieciowe.controller.dto.RegisterResponseDto;
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
    public ResponseEntity<RegisterResponseDto> register(@RequestBody RegisterDto requsetbody){
        RegisterResponseDto dto = loginService.register(requsetbody);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);

}
    @PostMapping("/log")
    @PreAuthorize("permitAll()")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto requestBody) {
        LoginResponseDto dto = loginService.log(requestBody);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);

    }
    @DeleteMapping("/{username}")
    @PreAuthorize("hasRole('LIBRARIAN')")
    public void delete(@PathVariable String username) {
        loginService.DeleteByUsername(username);
    }

}



