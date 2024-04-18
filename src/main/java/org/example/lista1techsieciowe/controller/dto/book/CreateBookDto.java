package org.example.lista1techsieciowe.controller.dto.book;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO representing information required to create a book.
 */
public class CreateBookDto {
    @Schema(name = "title", example ="title" )
    private String title;

    @Schema(name = "author", example ="author" )
    private String author;

    @Schema(name = "isbn", example ="9788328700789" )
    private String isbn;

    @Schema(name = "publisher", example ="publisher" )
    private String publisher;

    @Schema(name = "yearOfPublish", example ="2024" )
    private Integer yearOfPublish;

    @Schema(name = "availableCopies", example ="5" )
    private Integer availableCopies;

    /**
     * Constructor for CreateBookDto.
     * @param title The title of the book.
     * @param author The author of the book.
     * @param isbn The ISBN of the book.
     * @param publisher The publisher of the book.
     * @param yearOfPublish The year of publication of the book.
     * @param availableCopies The number of available copies of the book.
     */
    public CreateBookDto(String title, String author, String isbn, String publisher, Integer yearOfPublish, Integer availableCopies) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publisher = publisher;
        this.yearOfPublish = yearOfPublish;
        this.availableCopies = availableCopies;
    }

    /**
     * Retrieves the title of the book.
     * @return The title of the book.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the book.
     * @param title The title of the book to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Retrieves the author of the book.
     * @return The author of the book.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the author of the book.
     * @param author The author of the book to set.
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Retrieves the ISBN of the book.
     * @return The ISBN of the book.
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Sets the ISBN of the book.
     * @param isbn The ISBN of the book to set.
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Retrieves the publisher of the book.
     * @return The publisher of the book.
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * Sets the publisher of the book.
     * @param publisher The publisher of the book to set.
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * Retrieves the year of publication of the book.
     * @return The year of publication of the book.
     */
    public Integer getYearOfPublish() {
        return yearOfPublish;
    }

    /**
     * Sets the year of publication of the book.
     * @param yearOfPublish The year of publication of the book to set.
     */
    public void setYearOfPublish(Integer yearOfPublish) {
        this.yearOfPublish = yearOfPublish;
    }

    /**
     * Retrieves the number of available copies of the book.
     * @return The number of available copies of the book.
     */
    public Integer getAvailableCopies() {
        return availableCopies;
    }

    /**
     * Sets the number of available copies of the book.
     * @param availableCopies The number of available copies of the book to set.
     */
    public void setAvailableCopies(Integer availableCopies) {
        this.availableCopies = availableCopies;
    }
}
