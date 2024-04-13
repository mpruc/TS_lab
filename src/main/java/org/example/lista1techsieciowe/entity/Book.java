package org.example.lista1techsieciowe.entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "book_id")
    @JsonProperty("bookId")
    private Integer bookId;

    @OneToOne(mappedBy = "book", fetch = FetchType.LAZY)
    private BookDetails bookDetails;
    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY )
    private List<Review> reviews;
    @OneToMany(mappedBy = "book")
    private List<Loan> loans;
    @Basic
    @Column(name = "isbn")
    private String isbn;
    @Basic
    @Column(name = "title")
    private String title;
    @Basic
    @Column(name = "author")
    private String author;
    @Basic
    @Column(name = "publisher")
    private String publisher;
    @Basic
    @Column(name = "year_of_publish")
    private Integer yearOfPublish;
    @Basic
    @Column(name = "available_copies")
    private Integer availableCopies;

    public BookDetails getBookDetails() {
        return bookDetails;
    }

    public void setBookDetails(BookDetails bookDetails) {
        this.bookDetails = bookDetails;
    }

    public void setBookId(Integer id) {
        this.bookId = id;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
    public void setYearOfPublish(Integer yearOfPublish) {
        this.yearOfPublish = yearOfPublish;
    }
    public void setAvailableCopies(Integer availableCopies) {
        this.availableCopies = availableCopies;
    }
    public Integer getBookId() {
        return bookId;
    }
    public String getIsbn() {
        return isbn;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public String getPublisher() {
        return publisher;
    }
    public Integer getYearOfPublish() {
        return yearOfPublish;
    }
    public Integer getAvailableCopies() {
        return availableCopies;
    }
    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }
}
