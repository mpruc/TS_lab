package org.example.lista1techsieciowe.controller;

import org.example.lista1techsieciowe.controller.dto.book.CreateBookDto;
import org.example.lista1techsieciowe.controller.dto.book.CreateBookResponseDto;
import org.example.lista1techsieciowe.controller.dto.book.GetBookDto;
import org.example.lista1techsieciowe.service.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")

public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService= bookService;
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/add")
    @PreAuthorize("hasRole('LIBRARIAN')")

    public @ResponseBody CreateBookResponseDto add(@RequestBody @Validated CreateBookDto book) {
        return bookService.addBook(book);
    }

    @GetMapping("/getAll")
    @PreAuthorize("permitAll()")
    public @ResponseBody Iterable <GetBookDto> getAllBooks(){
        return bookService.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public GetBookDto getBook (@PathVariable Integer id) {

        return bookService.getBook(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('LIBRARIAN')")
    public void delete(@PathVariable int id) {
        bookService.deleteBook(id);
    }
}
