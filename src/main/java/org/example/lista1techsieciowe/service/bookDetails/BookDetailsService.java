package org.example.lista1techsieciowe.service.bookDetails;

import org.example.lista1techsieciowe.controller.dto.bookDetails.BookDetailsDto;
import org.example.lista1techsieciowe.controller.dto.bookDetails.BookDetailsResponseDto;
import org.example.lista1techsieciowe.controller.dto.book.GetBookDto;
import org.example.lista1techsieciowe.entity.Book;
import org.example.lista1techsieciowe.entity.BookDetails;
import org.example.lista1techsieciowe.repository.BookDetailsRepository;
import org.example.lista1techsieciowe.repository.BookRepository;
import org.example.lista1techsieciowe.service.book.exceptions.BookDoesntExistException;
import org.example.lista1techsieciowe.service.bookDetails.exceptions.BookDetailsDoesntExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookDetailsService {
    private final BookDetailsRepository bookDetailsRepository;
    private final BookRepository bookRepository;
    @Autowired
    public BookDetailsService(BookDetailsRepository bookDetailsRepository, BookRepository bookRepository) {
        this.bookDetailsRepository = bookDetailsRepository;
        this.bookRepository = bookRepository;
    }
    public List<BookDetailsResponseDto> getAll() {
        List<BookDetails> bookDetails = (List<BookDetails>)  bookDetailsRepository.findAll();
        return bookDetails.stream().map(this::mapBookDetails).collect(Collectors.toList());

    }

    public BookDetailsResponseDto getBookDetails(Integer id) {
        BookDetails bookDetails = bookDetailsRepository.findById(id)
                .orElseThrow(() -> BookDetailsDoesntExistException.create(id));
        return mapBookDetails(bookDetails);
    }
    private BookDetailsResponseDto mapBookDetails(BookDetails bookDetails) {
        GetBookDto book = new GetBookDto(
                bookDetails.getBook().getBookId(),
                bookDetails.getBook().getIsbn(),
                bookDetails.getBook().getPublisher(),
                bookDetails.getBook().getAuthor(),
                bookDetails.getBook().getTitle(),
                bookDetails.getBook().getYearOfPublish(),
                bookDetails.getBook().getAvailableCopies()>0);
        return new BookDetailsResponseDto(bookDetails.getBookDetailsId(), bookDetails.getGenre(), bookDetails.getSummary(), bookDetails.getCoverImageUrl(), book.getId());
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
            throw BookDetailsDoesntExistException.create(id);
        }
        bookDetailsRepository.deleteById(id);
    }
}

