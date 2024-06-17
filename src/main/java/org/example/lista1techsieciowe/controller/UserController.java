package org.example.lista1techsieciowe.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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


/**
 * Controller handling operations related to users.
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
@PreAuthorize("isAuthenticated()")
@Tag(name = "User")

public class UserController {
    private final UserService userService;

    /**
     * Constructor for UserController.
     * @param userService Service responsible for user operations.
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Adds a new user.
     * @param dto DTO representing the user to be added.
     * @return DTO containing the created user.
     */
    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/add")
    @PreAuthorize("hasRole('LIBRARIAN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User added successfully" ),
            @ApiResponse(responseCode = "403", description = "Not authorized", content = @Content),
    })
    public GetUserDto addUser(@RequestBody CreateUserDto dto){
        return userService.addUser(dto);
    }

    /**
     * Retrieves all users.
     * @return Iterable containing all user DTOs.
     */
    @GetMapping("/getAll")
    @PreAuthorize("hasRole('LIBRARIAN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "403", description = "Not authorized", content = @Content)
    })
    public @ResponseBody Iterable <GetUserDto> getAllUsers(){
        return userService.getAll();
    }

    /**
     * Retrieves details of the currently authenticated user.
     * @param principal Principal object containing user information.
     * @return ResponseEntity containing the user details.
     */
    @GetMapping("/me")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found" ),
            @ApiResponse(responseCode = "403", description = "Not authorized", content = @Content)
    })
    public ResponseEntity<GetUserDto> getMe(Principal principal) {
        String username = principal.getName();
        GetUserDto dto =   userService.getUserByUsername(username);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    /**
     * Retrieves details of a specific user by ID.
     * @param id ID of the user to retrieve.
     * @return DTO containing the user details.
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('LIBRARIAN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found" ),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
            @ApiResponse(responseCode = "403", description = "Not authorized", content = @Content)
    })
    public GetUserDto getUser(@PathVariable Integer id) {
        return userService.getUser(id);
    }

    /**
     * Deletes a user by ID.
     * @param id ID of the user to delete.
     */
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted successfully" ),
            @ApiResponse(responseCode = "404", description = "Delete failed", content = @Content),
            @ApiResponse(responseCode = "403", description = "Not authorized", content = @Content)
    })
    public void delete(@PathVariable int id) {
        userService.deleteUser(id);
    }

    /**
     * Updates a user with new information.
     * @param id ID of the user to update.
     * @param updatedUser DTO containing updated user information.
     * @return ResponseEntity containing the updated user response.
     */
    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/update/{id}")
    @PreAuthorize("hasRole('LIBRARIAN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated successfully" ),
            @ApiResponse(responseCode = "404", description = "User update failed", content = @Content),
            @ApiResponse(responseCode = "403", description = "Not authorized", content = @Content)
    })
    public ResponseEntity<GetUserDto> updateUser(@PathVariable Integer id, @RequestBody @Validated CreateUserDto updatedUser) {
        GetUserDto dto = userService.updateUser(id, updatedUser);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
