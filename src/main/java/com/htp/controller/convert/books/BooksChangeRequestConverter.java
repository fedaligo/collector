package com.htp.controller.convert.books;


import com.htp.controller.requests.books.BooksUpdateRequest;
import com.htp.controller.requests.collection.CollectionUpdateRequest;
import com.htp.entity.books.Books;
import com.htp.entity.collection.Collection;
import com.htp.exceptions.EntityNotFoundException;
import com.htp.service.collection.CollectionService;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class BooksChangeRequestConverter extends BooksRequestConverter<CollectionUpdateRequest, Books> {
    public BooksChangeRequestConverter(CollectionService collectionService) {
        super(collectionService);
    }

    @Override
    public Books convert(CollectionUpdateRequest request) {
        Books books =
                ofNullable(entityManager.find(Books.class, request.getBooksId()))
                        .orElseThrow(() -> new EntityNotFoundException(Books.class, request.getBooksId()));
        return doConvert(books, request);
    }
}
