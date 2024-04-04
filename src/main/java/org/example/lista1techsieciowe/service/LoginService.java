package org.example.lista1techsieciowe.service;

import org.example.lista1techsieciowe.controller.dto.LoginDto;
import org.example.lista1techsieciowe.controller.dto.LoginResponseDto;
import org.example.lista1techsieciowe.controller.dto.RegisterDto;
import org.example.lista1techsieciowe.controller.dto.RegisterResponseDto;
import org.example.lista1techsieciowe.entity.Login;
import org.example.lista1techsieciowe.entity.User;
import org.example.lista1techsieciowe.repository.LoginRepository;
import org.example.lista1techsieciowe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final LoginRepository loginRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public LoginService(LoginRepository loginRepository, UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.loginRepository = loginRepository;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public RegisterResponseDto register(RegisterDto registerDto) {
        User user = new User();
        user.setEmail(registerDto.getEmail());
        User newUser = userRepository.save(user);

        Login login = new Login();
        login.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        login.setUsername(registerDto.getUsername());
        login.setRole(registerDto.getRole());
        login.setUser(newUser);
        loginRepository.save(login);

        return new RegisterResponseDto(login.getUsername(), login.getRole(), login.getLoginId());

    }
    public LoginResponseDto log (LoginDto dto) {
        Login login = loginRepository.findByUsername(dto.getUsername()).orElseThrow(RuntimeException :: new);
        if(!passwordEncoder.matches(dto.getPassword(), login.getPassword())) {
            throw new RuntimeException();
        }
        String token = jwtService.generateToken(login);
        return new LoginResponseDto(token);
    }
}