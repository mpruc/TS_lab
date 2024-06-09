package org.example.lista1techsieciowe.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.lista1techsieciowe.controller.dto.book.CreateBookDto;
import org.example.lista1techsieciowe.controller.dto.book.CreateBookResponseDto;
import org.example.lista1techsieciowe.controller.dto.bookDetails.BookDetailsDto;
import org.example.lista1techsieciowe.controller.dto.bookDetails.BookDetailsResponseDto;
import org.example.lista1techsieciowe.service.bookDetails.BookDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller handling operations related to book details.
 */
@RestController
@CrossOrigin
@RequestMapping("/bookDetails")
@Tag(name="Book details")

public class BookDetailsController {
    private final BookDetailsService bookDetailsService;

    /**
     * Constructor for BookDetailsController.
     * @param bookDetailsService Service responsible for book details operations.
     */
    @Autowired
    public BookDetailsController(BookDetailsService bookDetailsService) {
        this.bookDetailsService = bookDetailsService;
    }

    /**
     * Adds details for a book.
     * @param bookDetails DTO containing details of the book.
     * @return Response containing the created book details.
     */
    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/add")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Book details added successfully" ),
            @ApiResponse(responseCode = "404", description = "Book details not found", content = @Content ),
            @ApiResponse(responseCode = "403", description = "Not authorized", content = @Content)
    })
    @PreAuthorize("hasRole('LIBRARIAN')")
    public @ResponseBody BookDetailsResponseDto addBookDetails(@RequestBody BookDetailsDto bookDetails){
        return bookDetailsService.addBookDetails(bookDetails);
    }

    /**
     * Retrieves all book details.
     * @return ResponseEntity containing a list of book details.
     */
    @GetMapping("/getAll")
    @PreAuthorize("permitAll()")
    @ApiResponse(responseCode = "200")
    public ResponseEntity<List<BookDetailsResponseDto>> getAllBookDetails(){
        List<BookDetailsResponseDto> dto = bookDetailsService.getAll();
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    /**
     * Retrieves details of a specific book by ID.
     * @param id ID of the book details to retrieve.
     * @return ResponseEntity containing the book details.
     */
    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book details found" ),
            @ApiResponse(responseCode = "404", description = "Book details not found", content = @Content)
    })
    public ResponseEntity<BookDetailsResponseDto>getBookDetails(@PathVariable Integer id){
        BookDetailsResponseDto dto = bookDetailsService.getBookDetails(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    /**
     * Deletes book details by ID.
     * @param id ID of the book details to delete.
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('LIBRARIAN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted successfully" ),
            @ApiResponse(responseCode = "404", description = "Delete failed", content = @Content),
            @ApiResponse(responseCode = "403", description = "Not authorized", content = @Content)
    })
    public void delete(@PathVariable int id) {
        bookDetailsService.deleteBookDetails(id);
    }

    /**
     * Updates book details with new information.
     * @param id ID of the book details to update.
     * @param updatedBookDetails DTO containing updated book details information.
     * @return ResponseEntity containing the updated book details response.
     */
    @PostMapping("/update/{id}")
    @PreAuthorize("hasRole('LIBRARIAN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book details updated successfully" ),
            @ApiResponse(responseCode = "404", description = "Update failed", content = @Content),
            @ApiResponse(responseCode = "403", description = "Not authorized", content = @Content)
    })
    public ResponseEntity<BookDetailsResponseDto> updateBookDetails(@PathVariable Integer id, @RequestBody @Validated BookDetailsDto updatedBookDetails) {
        BookDetailsResponseDto dto = bookDetailsService.updateBookDetails(id, updatedBookDetails);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
