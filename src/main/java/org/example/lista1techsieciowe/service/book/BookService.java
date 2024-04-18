package org.example.lista1techsieciowe.service.book;

import org.example.lista1techsieciowe.controller.dto.book.CreateBookDto;
import org.example.lista1techsieciowe.controller.dto.book.CreateBookResponseDto;
import org.example.lista1techsieciowe.controller.dto.book.GetBookDto;
import org.example.lista1techsieciowe.entity.Book;
import org.example.lista1techsieciowe.repository.BookRepository;
import org.example.lista1techsieciowe.service.book.exceptions.BookDoesntExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service that provides operations related to books.
 */
@Service
public class BookService {
    private final BookRepository bookRepository;

    /**
     * Constructs a new BookService with the provided dependencies.
     *
     * @param bookRepository The repository for Book entities.
     */
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * Retrieves all books.
     *
     * @return A list of GetBookDto containing book details.
     */
    public List<GetBookDto> getAll() {
        List<Book> book = (List<Book>) bookRepository.findAll();
        return book.stream().map(this :: mapBook).collect(Collectors.toList());
    }

    /**
     * Retrieves a book by ID.
     *
     * @param id The ID of the book to retrieve.
     * @return A GetBookDto containing book details.
     * @throws BookDoesntExistException If the book with the specified ID is not found.
     */
    public GetBookDto getBook( Integer id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> BookDoesntExistException.create(id));
        return mapBook(book);
    }

    /**
     * Maps a Book entity to a GetBookDto.
     *
     * @param book The Book entity to map.
     * @return A GetBookDto containing book details.
     */
    private GetBookDto mapBook(Book book) {
        return new GetBookDto(
                book.getBookId(),
                book.getTitle(),
                book.getIsbn(),
                book.getAuthor(),
                book.getPublisher(),
                book.getYearOfPublish(),
                book.getAvailableCopies()>0
                );
    }

    /**
     * Adds a new book.
     *
     * @param book The CreateBookDto containing book details.
     * @return A CreateBookResponseDto containing the newly created book details.
     */
    public CreateBookResponseDto addBook(CreateBookDto book) {
        var bookEntity = new Book();
        bookEntity.setIsbn(book.getIsbn());
        bookEntity.setTitle(book.getTitle());
        bookEntity.setAuthor(book.getAuthor());
        bookEntity.setYearOfPublish(book.getYearOfPublish());
        bookEntity.setPublisher(book.getPublisher());
        bookEntity.setAvailableCopies(book.getAvailableCopies());

        var newBook = bookRepository.save(bookEntity);
        return new CreateBookResponseDto(
                newBook.getBookId(),
                newBook.getTitle(),
                newBook.getAuthor(),
                newBook.getIsbn(),
                newBook.getPublisher(),
                newBook.getYearOfPublish(),
                newBook.getAvailableCopies()
        );
    }

    /**
     * Deletes a book by ID.
     *
     * @param id The ID of the book to delete.
     * @throws BookDoesntExistException If the book with the specified ID is not found.
     */
    public void deleteBook(Integer id) {
        if (!bookRepository.existsById(id)) {
            throw BookDoesntExistException.create(id);
        }
        bookRepository.deleteById(id);
    }

    /**
     * Updates a book's details.
     *
     * @param id          The ID of the book to update.
     * @param updatedBook The CreateBookDto containing updated book details.
     * @return A CreateBookResponseDto containing the updated book details.
     * @throws BookDoesntExistException If the book with the specified ID is not found.
     */
    public CreateBookResponseDto updateBook(Integer id, CreateBookDto updatedBook) {

        Book existingBook = bookRepository.findById(id)
                .orElseThrow(()-> BookDoesntExistException.create(id));

        existingBook.setIsbn(updatedBook.getIsbn());
        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setYearOfPublish(updatedBook.getYearOfPublish());
        existingBook.setPublisher(updatedBook.getPublisher());
        existingBook.setAvailableCopies(updatedBook.getAvailableCopies());

        Book savedBook = bookRepository.save(existingBook);

        return mapToCreateBookResponseDto(savedBook);
    }

    /**
     * Maps a Book entity to a CreateBookResponseDto.
     *
     * @param book The Book entity to map.
     * @return A CreateBookResponseDto containing book details.
     */
    private CreateBookResponseDto mapToCreateBookResponseDto(Book book) {
        return new CreateBookResponseDto(
                book.getBookId(),
                book.getTitle(),
                book.getAuthor(),
                book.getIsbn(),
                book.getPublisher(),
                book.getYearOfPublish(),
                book.getAvailableCopies()
        );
    }
}
