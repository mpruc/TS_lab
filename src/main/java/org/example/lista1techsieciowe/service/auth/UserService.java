package org.example.lista1techsieciowe.service.auth;

import org.example.lista1techsieciowe.controller.dto.user.CreateUserDto;
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

/**
 * Service that provides operations related to users.
 */
@Service
public class UserService {
    private final UserRepository userRepository;
    private final LoginRepository loginRepository;

    /**
     * Constructs a new UserService with the provided dependencies.
     *
     * @param userRepository  The repository for User entities.
     * @param loginRepository The repository for Login entities.
     */
    @Autowired
    public UserService(UserRepository userRepository, LoginRepository loginRepository) {
        this.userRepository = userRepository;
        this.loginRepository = loginRepository;
    }

    /**
     * Retrieves all users.
     *
     * @return A list of GetUserDto containing user details.
     */
    public List<GetUserDto> getAll() {
        List<User> user = (List<User>) userRepository.findAll();
        return user.stream().map(this::mapUser).collect(Collectors.toList());
    }
    /**
     * Retrieves a user by ID.
     *
     * @param id The ID of the user to retrieve.
     * @return A GetUserDto containing user details.
     * @throws UserNotFoundException If the user with the specified ID is not found.
     */
    public GetUserDto getUser(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> UserNotFoundException.create(id));
        return mapUser(user);
    }

    /**
     * Retrieves a user by username.
     *
     * @param username The username of the user to retrieve.
     * @return A GetUserDto containing user details.
     * @throws UserWithGivenLoginDoesntExistException If the user with the specified username is not found.
     */
    public GetUserDto getUserByUsername(String username) {
        Login login = loginRepository.findByUsername(username)
                .orElseThrow(() -> UserWithGivenLoginDoesntExistException.create(username));
        User user = login.getUser();
        return mapUser(user);
    }

    /**
     * Maps a User entity to a GetUserDto.
     *
     * @param user The User entity to map.
     * @return A GetUserDto containing user details.
     */
    private GetUserDto mapUser(User user) {
        return new GetUserDto(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }

    /**
     * Adds a new user.
     *
     * @param user The CreateUserDto containing user details.
     * @return A GetUserDto containing the newly created user details.
     */
    public GetUserDto addUser(CreateUserDto user) {
        var userEntity = new User();
        userEntity.setEmail(user.getEmail());
        userEntity.setName(user.getName());

        var newUser = userRepository.save(userEntity);
        return mapUser(newUser);
    }

    /**
     * Deletes a user by ID.
     *
     * @param id The ID of the user to delete.
     * @throws UserNotFoundException If the user with the specified ID is not found.
     */
    public void deleteUser(Integer id) {
        userRepository.findById(id)
                        .orElseThrow(() -> UserNotFoundException.create(id));
        userRepository.deleteById(id);
    }

    /**
     * Updates a user's details.
     *
     * @param id          The ID of the user to update.
     * @param updatedUser The CreateUserDto containing updated user details.
     * @return A GetUserDto containing the updated user details.
     * @throws UserNotFoundException If the user with the specified ID is not found.
     */
    public GetUserDto updateUser(Integer id, CreateUserDto updatedUser) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> UserNotFoundException.create(id));
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setName(updatedUser.getName());
        User newUser = userRepository.save(existingUser);
        return mapUser(newUser);
    }
}
