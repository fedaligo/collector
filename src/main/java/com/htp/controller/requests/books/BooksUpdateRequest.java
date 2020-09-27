package com.htp.controller.requests.books;

import lombok.*;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class BooksUpdateRequest extends BooksCreateRequest{
    private Long booksId;
}
