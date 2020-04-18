package com.rest.API.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="book_id")
    private Long id;
    @NotBlank
    @Column(name="book_name")
    private String bookName;
    @NotBlank
    @Column(name="author_name")
    private String authorName;
    @NotBlank
    @Column(name="isbn")
    private String isbn;
    public Book(){
        super();
    }
    public Book(Long id, String bookName, String authorName, String isbn) {
        super();
        this.id = id;
        this.bookName = bookName;
        this.authorName = authorName;
        this.isbn=isbn;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getBookName() {
        return bookName;
    }
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    public String getAuthorName() {
        return authorName;
    }
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}