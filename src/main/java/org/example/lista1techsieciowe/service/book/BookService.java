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

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<GetBookDto> getAll() {

        List<Book> book = (List<Book>) bookRepository.findAll();
        return book.stream().map(this :: mapBook).collect(Collectors.toList());
    }

    public GetBookDto getBook( Integer id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> BookDoesntExistException.create(id));
        return mapBook(book);
    }
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

    public void deleteBook(Integer id) {
        if (!bookRepository.existsById(id)) {
            throw BookDoesntExistException.create(id);
        }
        bookRepository.deleteById(id);
    }
}
