package org.example.lista1techsieciowe.service.auth;

import org.example.lista1techsieciowe.controller.dto.user.CreateUserDto;
import org.example.lista1techsieciowe.controller.dto.user.CreateUserResponseDto;
import org.example.lista1techsieciowe.controller.dto.user.GetUserDto;
import org.example.lista1techsieciowe.entity.Login;
import org.example.lista1techsieciowe.entity.User;
import org.example.lista1techsieciowe.repository.LoginRepository;
import org.example.lista1techsieciowe.repository.UserRepository;
import org.example.lista1techsieciowe.service.auth.exceptions.UserNotFoundException;
import org.example.lista1techsieciowe.service.auth.exceptions.UserWithGivenLoginDoesntExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final LoginRepository loginRepository;

    @Autowired
    public UserService(UserRepository userRepository, LoginRepository loginRepository) {
        this.userRepository = userRepository;
        this.loginRepository = loginRepository;
    }

    public List<GetUserDto> getAll() {
        List<User> user = (List<User>) userRepository.findAll();
        return user.stream().map(this::mapUser).collect(Collectors.toList());
    }

    public GetUserDto getUser(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> UserNotFoundException.create(id));
        return mapUser(user);
    }

    public GetUserDto getUserByUsername(String username) {
        Login login = loginRepository.findByUsername(username)
                .orElseThrow(() -> UserWithGivenLoginDoesntExistException.create(username));
        User user = login.getUser();
        return mapUser(user);
    }

    private GetUserDto mapUser(User user) {
        return new GetUserDto(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }

    public CreateUserResponseDto addUser(CreateUserDto user) {
        var userEntity = new User();
        userEntity.setEmail(user.getEmail());
        userEntity.setName(user.getName());

        var newUser = userRepository.save(userEntity);
        return mapToCreateUserResponseDto(newUser);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    public CreateUserResponseDto updateUser(Integer id, CreateUserDto updatedUser) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> UserNotFoundException.create(id));
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setName(updatedUser.getName());
        User newUser = userRepository.save(existingUser);
        return mapToCreateUserResponseDto(newUser);
    }

    private CreateUserResponseDto mapToCreateUserResponseDto(User user) {
        return new CreateUserResponseDto(
                user.getId(),
                user.getEmail(),
                user.getName()
        );
    }
}
