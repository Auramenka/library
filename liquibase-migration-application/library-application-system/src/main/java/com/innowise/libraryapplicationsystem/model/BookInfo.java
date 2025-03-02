package com.innowise.libraryapplicationsystem.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "book_info")
public class BookInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private Integer year;

    @OneToMany(mappedBy = "bookInfo", cascade = CascadeType.ALL)
    private List<Book> books = new ArrayList<>();

    @OneToMany(mappedBy = "bookInfo", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    @ManyToMany(mappedBy = "bookInfos")
    private List<Author> authors;

}
