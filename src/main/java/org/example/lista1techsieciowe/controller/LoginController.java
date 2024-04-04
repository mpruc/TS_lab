package org.example.lista1techsieciowe.controller;

import org.example.lista1techsieciowe.controller.dto.LoginDto;
import org.example.lista1techsieciowe.controller.dto.LoginResponseDto;
import org.example.lista1techsieciowe.controller.dto.RegisterDto;
import org.example.lista1techsieciowe.controller.dto.RegisterResponseDto;
import org.example.lista1techsieciowe.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    private final LoginService loginService;
    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RegisterResponseDto> register(@RequestBody RegisterDto requestBody) {
        RegisterResponseDto registerResponseDto = loginService.register(requestBody);
        return new ResponseEntity<>(registerResponseDto, HttpStatus.CREATED);

}
    @PostMapping("/login")
    @PreAuthorize("permitAll()")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto requestBody) {
        LoginResponseDto dto = loginService.log(requestBody);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);

    }


}
