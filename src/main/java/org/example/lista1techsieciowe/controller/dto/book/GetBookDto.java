package org.example.lista1techsieciowe.controller.dto.book;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO representing the details of a book retrieved from the database.
 */
public class GetBookDto {
    @Schema(name = "id", example ="1" )
    private Integer id;

    @Schema(name = "title", example ="title" )
    private String title;

    @Schema(name = "isbn", example ="9788328700789" )
    private String isbn;

    @Schema(name = "author", example ="author" )
    private String author;

    @Schema(name = "publisher", example ="publisher" )
    private String publisher;

    @Schema(name = "year of publish", example ="2024" )
    private Integer yearOfPublish;

    @Schema(name = "available", example ="true" )
    private boolean isAvailable;

    /**
     * Constructor for GetBookDto.
     * @param id The ID of the book.
     * @param title The title of the book.
     * @param isbn The ISBN of the book.
     * @param author The author of the book.
     * @param publisher The publisher of the book.
     * @param yearOfPublish The year of publication of the book.
     * @param isAvailable The availability status of the book.
     */
    public GetBookDto(Integer id, String title, String isbn, String author, String publisher, Integer yearOfPublish, boolean isAvailable) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.author = author;
        this.publisher = publisher;
        this.yearOfPublish = yearOfPublish;
        this.isAvailable = isAvailable;
    }

    /**
     * Retrieves the ID of the book.
     * @return The ID of the book.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the ID of the book.
     * @param id The ID of the book to set.
     */
    public void setId(Integer id) {
        this.id = id;
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
     * Retrieves the availability status of the book.
     * @return The availability status of the book.
     */
    public boolean isAvailable() {
        return isAvailable;
    }

    /**
     * Sets the availability status of the book.
     * @param available The availability status of the book to set.
     */
    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
