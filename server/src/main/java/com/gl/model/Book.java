package com.gl.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="books")
public class Book {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String title;
    private String author;
    private String year;
    private int pages;
    private String publisher;
    private Genre genre;

    public Book(String title, String author, String year, int pages, String publisher, Genre genre) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.pages = pages;
        this.publisher = publisher;
        this.genre = genre;
    }
}