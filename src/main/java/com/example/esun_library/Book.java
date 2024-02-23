package com.example.esun_library;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "ISBN", "Name", "Author", "introduction" })
public class Book {
    private String ISBN;
    private String Name;
    private String Author;
    private String introduction;

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getName() {
        return Name;
    }

    public String getAuthor() {
        return Author;
    }

    public String getIntroduction() {
        return introduction;
    }
}
