package org.example.lista1techsieciowe.service.auth;
import org.example.lista1techsieciowe.controller.dto.user.CreateUserDto;
import org.example.lista1techsieciowe.controller.dto.user.CreateUserResponseDto;
import org.example.lista1techsieciowe.controller.dto.user.GetUserDto;
import org.example.lista1techsieciowe.entity.User;
import org.example.lista1techsieciowe.repository.UserRepository;
import org.example.lista1techsieciowe.service.auth.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public List<GetUserDto> getAll() {
        List<User> user = (List<User>) userRepository.findAll();
        return user.stream().map(this :: mapUser).collect(Collectors.toList());
    }

    public GetUserDto getUser(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> UserNotFoundException.create(id));
        return mapUser(user);
    }
    private GetUserDto mapUser (User user) {
        return new GetUserDto(
                user.getUserId(),
                user.getName(),
                user.getEmail()
        );
    }

    public CreateUserResponseDto addUser(CreateUserDto user) {
        var userEntity = new User();
        userEntity.setUsername(user.getUsername());
        userEntity.setEmail(user.getEmail());
        userEntity.setName(user.getName());

        var newUser = userRepository.save(userEntity);
        return new CreateUserResponseDto(
                newUser.getUserId(),
                newUser.getUsername(),
                newUser.getEmail(),
                newUser.getName()
        );
    }
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}
