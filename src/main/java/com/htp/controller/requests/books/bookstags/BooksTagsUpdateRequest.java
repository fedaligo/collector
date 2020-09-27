package com.htp.controller.requests.books.bookstags;

import lombok.*;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class BooksTagsUpdateRequest extends BooksTagsCreateRequest{
    private Long booksTagsId;
}
