package org.example.lista1techsieciowe.controller;

import org.example.lista1techsieciowe.entity.BookDetails;
import org.example.lista1techsieciowe.service.BookDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public @ResponseBody BookDetails addBookDetails(@RequestBody BookDetails bookDetails){
        return bookDetailsService.addBookDetails(bookDetails);
    }

    @GetMapping("/getAll")
    public @ResponseBody Iterable<BookDetails> getAllBookDetails(){
        return bookDetailsService.getAll();
    }

    @GetMapping("/{id}")
    public BookDetails getBookDetails(@PathVariable int id) {
        return bookDetailsService.getBookDetails(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        bookDetailsService.deleteBookDetails(id);
    }
}
