package com.htp.controller.convert.books.bookstags;


import com.htp.controller.requests.books.bookstags.BooksTagsUpdateRequest;
import com.htp.controller.requests.collection.CollectionUpdateRequest;
import com.htp.entity.collection.Collection;
import com.htp.entity.tags.BadgesTags;
import com.htp.entity.tags.BooksTags;
import com.htp.exceptions.EntityNotFoundException;
import com.htp.service.books.BooksService;
import com.htp.service.tags.BadgesTagsService;
import com.htp.service.tags.BooksTagsService;
import com.htp.service.tags.TagsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
@RequiredArgsConstructor
public class BooksTagsChangeRequestConverter extends BooksTagsRequestConverter<CollectionUpdateRequest, BooksTags> {

    private final TagsService tagsService;
    private final BooksTagsService booksTagsService;

    @Override
    public BooksTags convert(CollectionUpdateRequest request) {
        try{
            Long tagsId = tagsService.getTagsByName(request.getTagsIterate()).getId();
            BooksTags booksTags = booksTagsService.findBooksTagsByParameters(tagsId, request.getBooksId());
            return doConvert(booksTags, request);
        } catch (NullPointerException e){
            BooksTags booksTags = new BooksTags();
            return doConvert(booksTags, request);
        }
    }
}
