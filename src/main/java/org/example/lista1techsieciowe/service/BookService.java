package org.example.lista1techsieciowe.service;

import org.example.lista1techsieciowe.entity.Book;
import org.example.lista1techsieciowe.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public List<Book> getAll() {
        return (List<Book>) bookRepository.findAll();
    }

    public Book getBook(int id) {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
    }
    public Book addBook(Book book) { return bookRepository.save(book);
    }
    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }
}
