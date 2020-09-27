package com.htp.controller.requests.books;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BooksCreateRequest {
    @Size(min = 1, max = 100)
    private String author;

    private int pages;

    @Size(min = 1, max = 100)
    private String publishingHouse;
}
