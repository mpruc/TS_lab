package org.example.lista1techsieciowe.controller;

import org.example.lista1techsieciowe.controller.dto.review.ReviewDto;
import org.example.lista1techsieciowe.controller.dto.review.ReviewResponseDto;
import org.example.lista1techsieciowe.controller.dto.user.CreateUserDto;
import org.example.lista1techsieciowe.controller.dto.user.CreateUserResponseDto;
import org.example.lista1techsieciowe.controller.dto.user.GetUserDto;
import org.example.lista1techsieciowe.service.auth.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/user")
@PreAuthorize("isAuthenticated()")

public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/add")
    @PreAuthorize("hasRole('LIBRARIAN')")
    public CreateUserResponseDto addUser(@RequestBody CreateUserDto dto){
        return userService.addUser(dto);
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasRole('LIBRARIAN')")
    public @ResponseBody Iterable <GetUserDto> getAllUsers(){

        return userService.getAll();
    }

    @GetMapping("/me")
    public ResponseEntity<GetUserDto> getMe(Principal principal) {
        String username = principal.getName();
        GetUserDto dto =   userService.getUserByUsername(username);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('LIBRARIAN')")
    public GetUserDto getUser(@PathVariable Integer id) {

        return userService.getUser(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        userService.deleteUser(id);
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/update/{id}")
    @PreAuthorize("hasRole('LIBRARIAN')")
    public ResponseEntity<CreateUserResponseDto> updateReview(@PathVariable Integer id, @RequestBody @Validated CreateUserDto updatedUser) {
        CreateUserResponseDto dto = userService.updateUser(id, updatedUser);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
