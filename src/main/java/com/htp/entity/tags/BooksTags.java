package com.htp.entity.tags;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.htp.entity.books.Books;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "books_tags")
public class BooksTags {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_books")
    private Books booksBooksTags;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tags")
    private Tags tagsBooksTags;
}
