package org.example.lista1techsieciowe.controller;

import org.example.lista1techsieciowe.controller.dto.user.CreateUserDto;
import org.example.lista1techsieciowe.controller.dto.user.CreateUserResponseDto;
import org.example.lista1techsieciowe.controller.dto.user.GetUserDto;
import org.example.lista1techsieciowe.service.auth.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@PreAuthorize("hasRole('LIBRARIAN')")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/add")
    public CreateUserResponseDto addUser(@RequestBody CreateUserDto dto){
        return userService.addUser(dto);
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasRole('LIBRARIAN')")
    public @ResponseBody Iterable <GetUserDto> getAllUsers(){

        return userService.getAll();
    }

    @GetMapping("/{id}")
    public GetUserDto getUser(@PathVariable Integer id) {

        return userService.getUser(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        userService.deleteUser(id);
    }
}
