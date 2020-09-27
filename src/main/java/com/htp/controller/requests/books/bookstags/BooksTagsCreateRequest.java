package com.htp.controller.requests.books.bookstags;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.PositiveOrZero;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BooksTagsCreateRequest {
    @PositiveOrZero
    private Long idBooks;

    @PositiveOrZero
    private Long idTags;
}
