package org.example.lista1techsieciowe.controller.dto.bookDetails;

import io.swagger.v3.oas.annotations.media.Schema;

public class BookDetailsResponseDto {
    @Schema(name = "id", example ="1" )
    private Integer id;
    @Schema(name = "genre", example ="fantasy" )
    private String genre;
    @Schema(name = "summary", example ="summary" )
    private String summary;
    @Schema(name = "coverImageUrl", example ="url" )
    private  String coverImageURL;
    @Schema(name = "book", example ="1" )
    private Integer book;

    public BookDetailsResponseDto(Integer id, String genre, String summary, String coverImageURL, Integer book) {
        this.id = id;
        this.genre = genre;
        this.summary = summary;
        this.coverImageURL = coverImageURL;
        this.book = book;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getCoverImageURL() {
        return coverImageURL;
    }

    public void setCoverImageURL(String coverImageURL) {
        this.coverImageURL = coverImageURL;
    }

    public Integer getBook() {
        return book;
    }

    public void setBook(Integer book) {
        this.book = book;
    }
}

