package org.example.lista1techsieciowe.controller.dto.book;

import io.swagger.v3.oas.annotations.media.Schema;

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

    public CreateBookDto(String title, String author, String isbn, String publisher, Integer yearOfPublish, Integer availableCopies) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publisher = publisher;
        this.yearOfPublish = yearOfPublish;
        this.availableCopies = availableCopies;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getYearOfPublish() {
        return yearOfPublish;
    }

    public void setYearOfPublish(Integer yearOfPublish) {
        this.yearOfPublish = yearOfPublish;
    }

    public Integer getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(Integer availableCopies) {
        this.availableCopies = availableCopies;
    }
}
