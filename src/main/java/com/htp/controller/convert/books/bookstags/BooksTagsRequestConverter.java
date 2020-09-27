package com.htp.controller.convert.books.bookstags;

import com.htp.controller.convert.EntityConverter;
import com.htp.controller.requests.badges.badgestags.BadgesTagsCreateRequest;
import com.htp.controller.requests.books.bookstags.BooksTagsCreateRequest;
import com.htp.controller.requests.collection.CollectionCreateRequest;
import com.htp.entity.tags.BadgesTags;
import com.htp.entity.tags.BooksTags;
import com.htp.service.badges.BadgesService;
import com.htp.service.books.BooksService;
import com.htp.service.tags.TagsService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class BooksTagsRequestConverter<S, T> extends EntityConverter<S, T> {
    protected BooksTags doConvert(BooksTags booksTags, CollectionCreateRequest request) {
        return booksTags;
    }
}
