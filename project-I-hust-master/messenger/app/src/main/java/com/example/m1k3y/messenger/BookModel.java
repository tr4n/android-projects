package com.example.m1k3y.messenger;

public class BookModel {
    public String id;
    public String book;
    public String author;

    public BookModel(String id, String book, String author) {
        this.id = id;
        this.book = book;
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
