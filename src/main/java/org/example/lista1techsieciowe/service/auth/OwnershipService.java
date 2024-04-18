package org.example.lista1techsieciowe.service.auth;

import org.example.lista1techsieciowe.commonTypes.UserRole;
import org.example.lista1techsieciowe.entity.Login;
import org.example.lista1techsieciowe.repository.LoginRepository;
import org.example.lista1techsieciowe.repository.UserRepository;
import org.example.lista1techsieciowe.service.auth.exceptions.UserNotFoundException;
import org.example.lista1techsieciowe.service.auth.exceptions.UserWithGivenLoginDoesntExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * Service that handles ownership-related operations.
 */

@Service
public class OwnershipService {

    private final LoginRepository loginRepository;

    /**
     * Constructs a new OwnershipService with the provided dependencies.
     *
     * @param loginRepository The repository for Login entities.
     */
    @Autowired
    public OwnershipService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    /**
     * Checks if the specified username corresponds to the specified user ID.
     *
     * @param username The username to check.
     * @param userId   The user ID to compare against.
     * @return True if the username corresponds to the specified user ID, false otherwise.
     * @throws UserWithGivenLoginDoesntExistException If no user with the given username exists.
     */
    public boolean isOwner(String username, Integer userId) {
        if (username == null || userId == null) {
            return false;
        }

        Login login = loginRepository.findByUsername(username)
                .orElseThrow(() -> UserWithGivenLoginDoesntExistException.create(username));
        Integer loggedInUserId = login.getUser().getId();

        return userId.equals(loggedInUserId);
    }

}
