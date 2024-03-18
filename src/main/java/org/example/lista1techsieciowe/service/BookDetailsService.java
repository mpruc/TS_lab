package org.example.lista1techsieciowe.service;

import org.example.lista1techsieciowe.entity.Book;
import org.example.lista1techsieciowe.entity.BookDetails;
import org.example.lista1techsieciowe.repository.BookDetailsRepository;
import org.example.lista1techsieciowe.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookDetailsService {
    private final BookDetailsRepository bookDetailsRepository;
    @Autowired
    public BookDetailsService(BookDetailsRepository bookDetailsRepository) {
        this.bookDetailsRepository = bookDetailsRepository;
    }
    public List<BookDetails> getAll() {
        return (List<BookDetails>) bookDetailsRepository.findAll();
    }

    public BookDetails getBookDetails(int id) {
        return bookDetailsRepository.findById(id).orElseThrow(() -> new RuntimeException("Book details not found"));
    }
    public BookDetails addBookDetails(BookDetails bookDetails) {
        return bookDetailsRepository.save(bookDetails);
    }
    public void deleteBookDetails(int id) {
        bookDetailsRepository.deleteById(id);
    }
}

