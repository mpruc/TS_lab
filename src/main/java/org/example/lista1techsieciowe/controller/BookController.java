package org.example.lista1techsieciowe.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.lista1techsieciowe.controller.dto.book.CreateBookDto;
import org.example.lista1techsieciowe.controller.dto.book.CreateBookResponseDto;
import org.example.lista1techsieciowe.controller.dto.book.GetBookDto;
import org.example.lista1techsieciowe.service.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Controller handling operations related to books.
 */
@RestController
@CrossOrigin
@RequestMapping("/book")
@Tag(name = "Book")

public class BookController {
    private final BookService bookService;

    /**
     * Constructor for BookController.
     * @param bookService Service responsible for book operations.
     */
    @Autowired
    public BookController(BookService bookService) {
        this.bookService= bookService;
    }

    /**
     * Adds a new book.
     * @param book DTO representing the book to be added.
     * @return Response containing the created book.
     */
    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/add")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Book added successfully" ),
            @ApiResponse(responseCode = "403", description = "Not authorized", content = @Content)
    })
    @PreAuthorize("hasRole('LIBRARIAN')")
    public @ResponseBody CreateBookResponseDto add(@RequestBody @Validated CreateBookDto book) {
        return bookService.addBook(book);
    }

    /**
     * Retrieves all books.
     * @return Iterable of DTOs representing all books.
     */
    @GetMapping("/getAll")
    @PreAuthorize("permitAll()")
    @ApiResponse(responseCode = "200")
    public @ResponseBody Iterable <GetBookDto> getAllBooks(){
        return bookService.getAll();
    }

    /**
     * Retrieves a specific book by ID.
     * @param id ID of the book to retrieve.
     * @return DTO representing the retrieved book.
     */
    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book found" ),
            @ApiResponse(responseCode = "404", description = "Book not found", content = @Content)
    })
    public GetBookDto getBook (@PathVariable Integer id) {
        return bookService.getBook(id);
    }

    /**
     * Deletes a book by ID.
     * @param id ID of the book to delete.
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('LIBRARIAN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted successfully" ),
            @ApiResponse(responseCode = "404", description = "Delete failed", content = @Content),
            @ApiResponse(responseCode = "403", description = "Not authorized", content = @Content)
    })

    public void delete(@PathVariable int id) {
        bookService.deleteBook(id);
    }

    /**
     * Updates a book.
     * @param id ID of the book to update.
     * @param updatedBook DTO containing updated book information.
     * @return ResponseEntity containing the updated book response.
     */
    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/update/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book updated successfully" ),
            @ApiResponse(responseCode = "404", description = "Book not found", content = @Content),
            @ApiResponse(responseCode = "403", description = "Not authorized", content = @Content)
    })
    @PreAuthorize("hasRole('LIBRARIAN')")
    public ResponseEntity<CreateBookResponseDto> updateBook(@PathVariable Integer id, @RequestBody @Validated CreateBookDto updatedBook) {
        CreateBookResponseDto updatedBookResponse = bookService.updateBook(id, updatedBook);
        return ResponseEntity.ok(updatedBookResponse);
    }
}
