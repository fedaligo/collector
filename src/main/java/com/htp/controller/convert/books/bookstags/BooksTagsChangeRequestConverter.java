package com.htp.controller.convert.books.bookstags;


import com.htp.controller.requests.collection.CollectionUpdateRequest;
import com.htp.entity.tags.BooksTags;
import com.htp.service.tags.BooksTagsService;
import com.htp.service.tags.TagsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
