package com.htp.controller.convert.books.bookstags;


import com.htp.controller.requests.collection.CollectionCreateRequest;
import com.htp.entity.tags.BooksTags;
import org.springframework.stereotype.Component;

@Component
public class BooksTagsCreateRequestConverter extends BooksTagsRequestConverter<CollectionCreateRequest, BooksTags> {

    @Override
    public BooksTags convert(CollectionCreateRequest request) {
        BooksTags booksTags = new BooksTags();
        return doConvert(booksTags, request);
    }
}
