package org.example.lista1techsieciowe.service.auth;

import org.example.lista1techsieciowe.commonTypes.UserRole;
import org.example.lista1techsieciowe.entity.Login;
import org.example.lista1techsieciowe.repository.LoginRepository;
import org.example.lista1techsieciowe.repository.UserRepository;
import org.example.lista1techsieciowe.service.auth.exceptions.UserNotFoundException;
import org.example.lista1techsieciowe.service.auth.exceptions.UserWithGivenLoginDoesntExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OwnershipService {

    private final LoginRepository loginRepository;
    private UserRepository userRepository;

    @Autowired
    public OwnershipService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public boolean isOwner(Integer username, Integer userId) {
        if (username == null || username == null) {
            return false;
        }

        Login login = loginRepository.findById(userId)
                .orElseThrow(() -> UserNotFoundException.create(userId));
        return userId.equals(login.getUser().getId());

    }
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
