package org.example.lista1techsieciowe.controller;

import org.example.lista1techsieciowe.entity.Book;
import org.example.lista1techsieciowe.repository.BookRepository;
import org.example.lista1techsieciowe.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public @ResponseBody Book addBook(@RequestBody Book book){
        return bookService.addBook(book);
    }

    @GetMapping("/getAll")
    public @ResponseBody Iterable <Book> getAllBooks(){
        return bookService.getAll();
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable int id) {
        return bookService.getBook(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        bookService.deleteBook(id);
    }
}
