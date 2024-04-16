package org.example.lista1techsieciowe.controller;

import org.example.lista1techsieciowe.controller.dto.auth.LoginDto;
import org.example.lista1techsieciowe.controller.dto.auth.LoginResponseDto;
import org.example.lista1techsieciowe.controller.dto.auth.RegisterDto;
import org.example.lista1techsieciowe.controller.dto.auth.RegisterResponseDto;
import org.example.lista1techsieciowe.controller.dto.loan.CreateLoanDto;
import org.example.lista1techsieciowe.controller.dto.loan.CreateLoanResponseDto;
import org.example.lista1techsieciowe.controller.dto.user.CreateUserDto;
import org.example.lista1techsieciowe.controller.dto.user.CreateUserResponseDto;
import org.example.lista1techsieciowe.service.auth.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
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

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/update/{id}")
    @PreAuthorize("hasRole('LIBRARIAN')")
    public ResponseEntity<RegisterResponseDto> updateLogin(@PathVariable Integer id, @RequestBody @Validated RegisterDto updatedLogin) {
        RegisterResponseDto dto = loginService.updateLogin(id, updatedLogin);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}



