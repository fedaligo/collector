package com.htp.entity.books;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.htp.entity.collection.Collection;
import com.htp.entity.tags.BooksTags;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = {"id","booksTags"})
@ToString(exclude = {"booksTags"})
@Entity
@Table(name = "books")
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column
    private String author;

    @Column
    private int pages;

    @Column(name = "publishing_house")
    private String publishingHouse;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_collection")
    private Collection collectionBooks;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.ALL},
            orphanRemoval = true,
            mappedBy = "booksBooksTags")
    private Set<BooksTags> booksTags = Collections.emptySet();
}
