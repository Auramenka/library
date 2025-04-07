package com.innowise.libraryapplicationsystem.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "Title can't be empty")
    @Size(min = 2, max = 40, message = "Title must be between 2 and 40 characters")
    @Column(unique = true, nullable = false)
    private String title;

    @NotBlank(message = "Description can't be empty")
    @Size(min = 10, max = 1000, message = "Description must be between 10 and 1000 characters")
    @Column(unique = true, nullable = false)
    private String description;

    @Min(value = 1000, message = "Year must be greater than or equal to 1000")
    @Column(nullable = false)
    private Integer year;

    @OneToMany(mappedBy = "bookInfo", cascade = CascadeType.ALL)
    private List<Book> books = new ArrayList<>();

    @OneToMany(mappedBy = "bookInfo", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    @ManyToMany(mappedBy = "bookInfos")
    private List<Author> authors;

}
