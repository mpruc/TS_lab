package org.example.lista1techsieciowe.controller.dto.book;

import io.swagger.v3.oas.annotations.media.Schema;

public class CreateBookResponseDto {

    @Schema(name = "id", example = "1")
    private Integer id;

    @Schema(name = "title", example ="Title" )
    private String title;

    @Schema(name = "author", example ="author" )
    private String author;

    @Schema(name = "isbn", example ="9781234567890" )
    private String isbn;

    @Schema(name = "publisher", example ="Publisher" )
    private String publisher;

    @Schema(name = "yearOfPublish", example ="2024" )
    private Integer yearOfPublish;

    @Schema(name = "availableCopies", example ="10" )
    private Integer availableCopies;

    public CreateBookResponseDto(Integer id, String title, String author, String isbn, String publisher, Integer yearOfPublish, Integer availableCopies) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publisher = publisher;
        this.yearOfPublish = yearOfPublish;
        this.availableCopies = availableCopies;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
