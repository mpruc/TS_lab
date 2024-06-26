package org.example.lista1techsieciowe.service.auth;

import jakarta.transaction.Transactional;
import org.example.lista1techsieciowe.controller.dto.auth.LoginDto;
import org.example.lista1techsieciowe.controller.dto.auth.LoginResponseDto;
import org.example.lista1techsieciowe.controller.dto.auth.RegisterDto;
import org.example.lista1techsieciowe.controller.dto.auth.RegisterResponseDto;
import org.example.lista1techsieciowe.entity.Login;
import org.example.lista1techsieciowe.entity.User;
import org.example.lista1techsieciowe.repository.LoginRepository;
import org.example.lista1techsieciowe.repository.UserRepository;
import org.example.lista1techsieciowe.service.JwtService;
import org.example.lista1techsieciowe.service.auth.exceptions.IncorrectPasswordException;
import org.example.lista1techsieciowe.service.auth.exceptions.UserAlreadyExistsException;
import org.example.lista1techsieciowe.service.auth.exceptions.UserNotFoundException;
import org.example.lista1techsieciowe.service.auth.exceptions.UserWithGivenLoginDoesntExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service that handles user authentication operations.
 */
@Service
public class LoginService {
    private final LoginRepository loginRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructs a new LoginService with the provided dependencies.
     *
     * @param loginRepository  The repository for Login entities.
     * @param userRepository   The repository for User entities.
     * @param jwtService       The JWT service for token operations.
     * @param passwordEncoder  The password encoder for encoding passwords.
     */
    @Autowired
    public LoginService(LoginRepository loginRepository, UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.loginRepository = loginRepository;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Registers a new user.
     *
     * @param registerDto The DTO containing registration details.
     * @return A RegisterResponseDto containing registration information.
     * @throws UserAlreadyExistsException If a user with given username already exists.
     */
    @Transactional
    public RegisterResponseDto register(RegisterDto registerDto) {
        Optional<Login> existingLogin = loginRepository.findByUsername(registerDto.getUsername());

        if(existingLogin.isPresent()) {
            throw UserAlreadyExistsException.create(registerDto.getUsername());
        }

        User user = new User();
        user.setEmail(registerDto.getEmail());
        user.setName(registerDto.getName());
        userRepository.save(user);

        Login login = new Login();
        login.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        login.setUsername(registerDto.getUsername());
        login.setRole(registerDto.getRole());
        login.setUser(user);
        loginRepository.save(login);

        return new RegisterResponseDto(login.getUsername(), login.getRole(), login.getLoginId());
    }
    /**
     * Logs in a user and generates a JWT token.
     *
     * @param dto The DTO containing login credentials.
     * @return A LoginResponseDto containing the JWT token.
     * @throws UserWithGivenLoginDoesntExistException If user with given username doesn't exist.
     * @throws IncorrectPasswordException If the provided password is incorrect.
     */
    public LoginResponseDto log (LoginDto dto) {
        Login login = loginRepository.findByUsername(dto.getUsername()).orElseThrow(()-> UserWithGivenLoginDoesntExistException.create(dto.getUsername()));
        if(!passwordEncoder.matches(dto.getPassword(), login.getPassword())) {
            throw IncorrectPasswordException.create();
        }
        String token = jwtService.generateToken(login);
        return new LoginResponseDto(token);
    }
    /**
     * Deletes a user by username.
     *
     * @param username The username of the user to delete.
     * @throws UserWithGivenLoginDoesntExistException If no user with the given username exists.
     */
    public void DeleteByUsername(String username) {
        Login login = loginRepository.findByUsername(username).orElseThrow(() ->
                UserWithGivenLoginDoesntExistException.create(username)
        );
        loginRepository.delete(login);
    }
}