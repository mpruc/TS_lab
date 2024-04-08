package org.example.lista1techsieciowe.controller;

import org.example.lista1techsieciowe.controller.dto.BookDetailsDto;
import org.example.lista1techsieciowe.controller.dto.BookDetailsResponseDto;
import org.example.lista1techsieciowe.entity.BookDetails;
import org.example.lista1techsieciowe.service.bookDetails.BookDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookDetails")

public class BookDetailsController {
    private final BookDetailsService bookDetailsService;

    @Autowired
    public BookDetailsController(BookDetailsService bookDetailsService) {
        this.bookDetailsService = bookDetailsService;
    }
    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/add")
    @PreAuthorize("hasRole('LIBRARIAN')")

    public @ResponseBody BookDetailsResponseDto addBookDetails(@RequestBody BookDetailsDto bookDetails){
        return bookDetailsService.addBookDetails(bookDetails);
    }

    @GetMapping("/getAll")
    @PreAuthorize("permitAll()")
    public @ResponseBody Iterable<BookDetails> getAllBookDetails(){
        return bookDetailsService.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public BookDetails getBookDetails(@PathVariable int id) {
        return bookDetailsService.getBookDetails(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('LIBRARIAN')")
    public void delete(@PathVariable int id) {
        bookDetailsService.deleteBookDetails(id);
    }
}
