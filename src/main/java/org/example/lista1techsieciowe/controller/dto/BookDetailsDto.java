package org.example.lista1techsieciowe.controller.dto;

public class BookDetailsDto {
    private String genre;
    private String summary;
    private  String CoverImageURL;
    private Integer book;

    public BookDetailsDto(String genre, String summary, String coverImageURL, Integer book) {
        this.genre = genre;
        this.summary = summary;
        CoverImageURL = coverImageURL;
        this.book = book;
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
        return CoverImageURL;
    }

    public void setCoverImageURL(String coverImageURL) {
        CoverImageURL = coverImageURL;
    }

    public Integer getBook() {
        return book;
    }

    public void setBook(Integer book) {
        this.book = book;
    }
}
