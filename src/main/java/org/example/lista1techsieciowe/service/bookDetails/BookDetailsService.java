package org.example.lista1techsieciowe.service.bookDetails;

import org.example.lista1techsieciowe.entity.BookDetails;
import org.example.lista1techsieciowe.repository.BookDetailsRepository;
import org.example.lista1techsieciowe.service.book.exceptions.BookDoesntExistException;
import org.example.lista1techsieciowe.service.bookDetails.exceptions.BookDetailsDoesntExistException;
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

    public BookDetails getBookDetails(Integer id) {
        return bookDetailsRepository.findById(id).orElseThrow(() -> BookDetailsDoesntExistException.create(id));
    }
    public BookDetails addBookDetails(BookDetails bookDetails) {
        return bookDetailsRepository.save(bookDetails);
    }
    public void deleteBookDetails(Integer id) {
        if (!bookDetailsRepository.existsById(id)) {
            throw BookDoesntExistException.create(id);
        }
        bookDetailsRepository.deleteById(id);
    }
}

