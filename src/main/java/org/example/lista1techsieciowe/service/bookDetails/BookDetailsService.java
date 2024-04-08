package org.example.lista1techsieciowe.service.bookDetails;

import org.example.lista1techsieciowe.controller.dto.BookDetailsDto;
import org.example.lista1techsieciowe.controller.dto.BookDetailsResponseDto;
import org.example.lista1techsieciowe.controller.dto.LoanDto;
import org.example.lista1techsieciowe.controller.dto.LoanResponseDto;
import org.example.lista1techsieciowe.entity.Book;
import org.example.lista1techsieciowe.entity.BookDetails;
import org.example.lista1techsieciowe.entity.Loan;
import org.example.lista1techsieciowe.entity.User;
import org.example.lista1techsieciowe.repository.BookDetailsRepository;
import org.example.lista1techsieciowe.repository.BookRepository;
import org.example.lista1techsieciowe.repository.UserRepository;
import org.example.lista1techsieciowe.service.auth.exceptions.UserWithGivenLoginDoesntExistException;
import org.example.lista1techsieciowe.service.book.exceptions.BookDoesntExistException;
import org.example.lista1techsieciowe.service.bookDetails.exceptions.BookDetailsDoesntExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookDetailsService {
    private final BookDetailsRepository bookDetailsRepository;
    private final BookRepository bookRepository;
    @Autowired
    public BookDetailsService(BookDetailsRepository bookDetailsRepository, BookRepository bookRepository) {
        this.bookDetailsRepository = bookDetailsRepository;
        this.bookRepository = bookRepository;
    }
    public List<BookDetails> getAll() {
        return (List<BookDetails>) bookDetailsRepository.findAll();
    }

    public BookDetails getBookDetails(Integer id) {
        return bookDetailsRepository.findById(id).orElseThrow(() -> BookDetailsDoesntExistException.create(id));
    }
    public BookDetailsResponseDto addBookDetails(BookDetailsDto bookDetails) {
        Book book = bookRepository.findById(bookDetails.getBook())
                .orElseThrow(() -> BookDoesntExistException.create(bookDetails.getBook()));

        var bookDetEntity = new BookDetails();
        bookDetEntity.setBook(book);
        bookDetEntity.setGenre(bookDetails.getGenre());
        bookDetEntity.setSummary(bookDetails.getSummary());
        bookDetEntity.setCoverImageUrl(bookDetails.getCoverImageURL());

        var newBookDetails = bookDetailsRepository.save(bookDetEntity);
        return new BookDetailsResponseDto(newBookDetails.getBookDetailsId(),
                bookDetails.getGenre(),
                bookDetails.getSummary(),
                bookDetails.getCoverImageURL(),
                bookDetails.getBook());
    }

    public void deleteBookDetails(Integer id) {
        if (!bookDetailsRepository.existsById(id)) {
            throw BookDoesntExistException.create(id);
        }
        bookDetailsRepository.deleteById(id);
    }
}

