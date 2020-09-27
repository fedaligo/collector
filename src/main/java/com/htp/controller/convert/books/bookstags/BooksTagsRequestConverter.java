package com.htp.controller.convert.books.bookstags;

import com.htp.controller.convert.EntityConverter;
import com.htp.controller.requests.collection.CollectionCreateRequest;
import com.htp.entity.tags.BooksTags;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class BooksTagsRequestConverter<S, T> extends EntityConverter<S, T> {
    protected BooksTags doConvert(BooksTags booksTags, CollectionCreateRequest request) {
        return booksTags;
    }
}
