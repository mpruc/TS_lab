package org.example.lista1techsieciowe.controller.dto.bookDetails;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO representing the details of a book
 */
public class BookDetailsDto {
    @Schema(name = "genre", example ="fantasy" )
    private String genre;

    @Schema(name = "summary", example ="summary" )
    private String summary;

    @Schema(name = "book", example ="1" )
    private Integer book;

    @Schema(name = "coverImageURL", example ="url" )
    private String coverImageURL;

    /**
     * Constructor for BookDetailsDto.
     * @param genre The genre of the book.
     * @param summary The summary of the book.
     * @param coverImageURL The URL of the cover image of the book.
     * @param book The ID of the book associated with these details.
     */
    public BookDetailsDto(String genre, String summary, String coverImageURL, Integer book) {
        this.genre = genre;
        this.summary = summary;
        this.coverImageURL = coverImageURL;
        this.book = book;
    }

    /**
     * Retrieves the genre of the book.
     * @return The genre of the book.
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Sets the genre of the book.
     * @param genre The genre of the book to set.
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Retrieves the summary of the book.
     * @return The summary of the book.
     */
    public String getSummary() {
        return summary;
    }

    /**
     * Sets the summary of the book.
     * @param summary The summary of the book to set.
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * Retrieves the URL of the cover image of the book.
     * @return The URL of the cover image of the book.
     */
    public String getCoverImageURL() {
        return coverImageURL;
    }

    /**
     * Sets the URL of the cover image of the book.
     * @param coverImageURL The URL of the cover image of the book to set.
     */
    public void setCoverImageURL(String coverImageURL) {
        this.coverImageURL = coverImageURL;
    }

    /**
     * Retrieves the ID of the book associated with these details.
     * @return The ID of the book.
     */
    public Integer getBook() {
        return book;
    }

    /**
     * Sets the ID of the book associated with these details.
     * @param book The ID of the book to set.
     */
    public void setBook(Integer book) {
        this.book = book;
    }
}
