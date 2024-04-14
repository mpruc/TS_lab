package org.example.lista1techsieciowe.controller;

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
    public ResponseEntity<List<BookDetailsResponseDto>> getAllBookDetails(){
        List<BookDetailsResponseDto> dto = bookDetailsService.getAll();
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<BookDetailsResponseDto>getBookDetails(@PathVariable Integer id){
        BookDetailsResponseDto dto = bookDetailsService.getBookDetails(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('LIBRARIAN')")
    public void delete(@PathVariable int id) {
        bookDetailsService.deleteBookDetails(id);
    }
    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/update/{id}")
    @PreAuthorize("hasRole('LIBRARIAN')")
    public ResponseEntity<BookDetailsResponseDto> updateBookDetails(@PathVariable Integer id, @RequestBody @Validated BookDetailsDto updatedBookDetails) {
        BookDetailsResponseDto dto = bookDetailsService.updateBookDetails(id, updatedBookDetails);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
