package org.example.lista1techsieciowe.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "book_details")
public class BookDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_details_id")
    private Integer bookDetailsId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @Basic
    @Column(name = "genre")
    private String genre;

    @Basic
    @Column(name = "summary")
    private String summary;

    @Basic
    @Column(name = "cover_image_url")
    private String coverImageUrl;

    public Integer getBookDetailsId() {
        return bookDetailsId;
    }

    public void setBookDetailsId(Integer bookDetailsId) {
        this.bookDetailsId = bookDetailsId;
    }


    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
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

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }
}
