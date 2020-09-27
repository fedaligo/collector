package com.htp.controller.convert.books;


import com.htp.controller.requests.collection.CollectionCreateRequest;
import com.htp.entity.books.Books;
import com.htp.service.collection.CollectionService;
import org.springframework.stereotype.Component;

@Component
public class BooksCreateRequestConverter extends BooksRequestConverter<CollectionCreateRequest, Books> {
    public BooksCreateRequestConverter(CollectionService collectionService) {
        super(collectionService);
    }

    @Override
    public Books convert(CollectionCreateRequest request) {
        Books books = new Books();
        return doConvert(books, request);
    }
}
