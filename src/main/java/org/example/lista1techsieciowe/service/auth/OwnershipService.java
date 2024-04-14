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
        return userId == login.getUser().getId();

    }
    public boolean isReviewOwner(Integer userId, Integer reviewUserId) {
        return userId.equals(reviewUserId);
    }
    public boolean isLibrarian(Integer userId) {
        Login login = loginRepository.findById(userId)
                .orElseThrow(() -> UserNotFoundException.create(userId));
        return login.getRole() == UserRole.ROLE_LIBRARIAN;
    }


}
