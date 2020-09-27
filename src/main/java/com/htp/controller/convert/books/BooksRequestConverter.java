package com.htp.controller.convert.books;

import com.htp.controller.convert.EntityConverter;
import com.htp.controller.requests.books.BooksCreateRequest;
import com.htp.controller.requests.books.bookstags.BooksTagsCreateRequest;
import com.htp.controller.requests.collection.CollectionCreateRequest;
import com.htp.entity.books.Books;
import com.htp.entity.tags.BooksTags;
import com.htp.service.collection.CollectionService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class BooksRequestConverter<S, T> extends EntityConverter<S, T> {

    private final CollectionService collectionService;

    protected Books doConvert(Books books, CollectionCreateRequest request) {
        books.setAuthor(request.getAuthor());
        books.setPages(request.getPages());
        books.setPublishingHouse(request.getPublishingHouse());
        books.setCollectionBooks(collectionService.findItemById(request.getIdCollection()));
        return books;
    }
}
