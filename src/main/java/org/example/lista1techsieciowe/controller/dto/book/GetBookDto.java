package org.example.lista1techsieciowe.controller.dto.book;

import io.swagger.v3.oas.annotations.media.Schema;

public class GetBookDto {
    @Schema(name = "id", example ="1" )
    private Integer id;
    @Schema(name = "title", example ="title" )
    private String title;
    @Schema(name = "title", example ="9788328700789" )
    private String isbn;
    @Schema(name = "author", example ="author" )
    private String author;
    @Schema(name = "publisher", example ="publisher" )
    private String publisher;
    @Schema(name = "year of publish", example ="2024" )
    private Integer yearOfPublish;
    @Schema(name = "available", example ="true" )
    private boolean isAvailable;

    public GetBookDto(Integer id, String title, String isbn, String author, String publisher, Integer yearOfPublish, boolean isAvailable) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.author = author;
        this.publisher = publisher;
        this.yearOfPublish = yearOfPublish;
        this.isAvailable = isAvailable;
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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
