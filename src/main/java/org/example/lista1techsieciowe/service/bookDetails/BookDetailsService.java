package org.example.lista1techsieciowe.service.bookDetails;

import org.example.lista1techsieciowe.controller.dto.book.CreateBookDto;
import org.example.lista1techsieciowe.controller.dto.book.CreateBookResponseDto;
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

/**
 * Service that provides operations related to book details.
 */
@Service
public class BookDetailsService {
    private final BookDetailsRepository bookDetailsRepository;
    private final BookRepository bookRepository;

    /**
     * Constructs a new BookDetailsService with the provided dependencies.
     *
     * @param bookDetailsRepository The repository for BookDetails entities.
     * @param bookRepository        The repository for Book entities.
     */
    @Autowired
    public BookDetailsService(BookDetailsRepository bookDetailsRepository, BookRepository bookRepository) {
        this.bookDetailsRepository = bookDetailsRepository;
        this.bookRepository = bookRepository;
    }

    /**
     * Retrieves all book details.
     *
     * @return A list of BookDetailsResponseDto containing book details.
     */
    public List<BookDetailsResponseDto> getAll() {
        List<BookDetails> bookDetails = (List<BookDetails>)  bookDetailsRepository.findAll();
        return bookDetails.stream().map(this::mapBookDetails).collect(Collectors.toList());

    }

    /**
     * Retrieves book details by ID.
     *
     * @param id The ID of the book details to retrieve.
     * @return A BookDetailsResponseDto containing book details.
     * @throws BookDetailsDoesntExistException If the book details with the specified ID is not found.
     */
    public BookDetailsResponseDto getBookDetails(Integer id) {
        BookDetails bookDetails = bookDetailsRepository.findById(id)
                .orElseThrow(() -> BookDetailsDoesntExistException.create(id));
        return mapBookDetails(bookDetails);
    }

    /**
     * Maps a BookDetails entity to a BookDetailsResponseDto.
     *
     * @param bookDetails The BookDetails entity to map.
     * @return A BookDetailsResponseDto containing book details.
     */
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

    /**
     * Adds book details for a specified book.
     *
     * @param bookDetails The DTO containing book details.
     * @return A BookDetailsResponseDto containing the added book details.
     * @throws BookDoesntExistException If the specified book does not exist.
     */
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

    /**
     * Deletes book details by ID.
     *
     * @param id The ID of the book details to delete.
     * @throws BookDetailsDoesntExistException If the book details with the specified ID is not found.
     */
    public void deleteBookDetails(Integer id) {
        if (!bookDetailsRepository.existsById(id)) {
            throw BookDetailsDoesntExistException.create(id);
        }
        bookDetailsRepository.deleteById(id);
    }

    /**
     * Updates book details with the specified ID.
     *
     * @param id               The ID of the book details to update.
     * @param updatedBookDetails The DTO containing updated book details.
     * @return A BookDetailsResponseDto containing the updated book details.
     * @throws BookDetailsDoesntExistException If the specified book details do not exist.
     * @throws BookDoesntExistException        If the specified book does not exist.
     */
    public BookDetailsResponseDto updateBookDetails(Integer id, BookDetailsDto updatedBookDetails) {
        BookDetails existingBookDetails = bookDetailsRepository.findById(id)
                .orElseThrow(() -> BookDetailsDoesntExistException.create(id));
        Book book = bookRepository.findById(updatedBookDetails.getBook())
                .orElseThrow(() -> BookDoesntExistException.create(updatedBookDetails.getBook()));

        existingBookDetails.setGenre(updatedBookDetails.getGenre());
        existingBookDetails.setSummary(updatedBookDetails.getSummary());
        existingBookDetails.setCoverImageUrl(updatedBookDetails.getCoverImageURL());
        existingBookDetails.setBook(book);

        BookDetails savedBookDetails = bookDetailsRepository.save(existingBookDetails);
        return mapBookDetails(savedBookDetails);

    }
}

