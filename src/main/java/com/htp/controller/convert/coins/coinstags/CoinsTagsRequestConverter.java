package com.htp.controller.convert.coins.coinstags;

import com.htp.controller.convert.EntityConverter;
import com.htp.controller.requests.books.bookstags.BooksTagsCreateRequest;
import com.htp.controller.requests.coins.coinstags.CoinsTagsCreateRequest;
import com.htp.controller.requests.collection.CollectionCreateRequest;
import com.htp.entity.tags.BooksTags;
import com.htp.entity.tags.CoinsTags;
import com.htp.service.badges.BadgesService;
import com.htp.service.coins.CoinsService;
import com.htp.service.tags.TagsService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class CoinsTagsRequestConverter<S, T> extends EntityConverter<S, T> {
    protected CoinsTags doConvert(CoinsTags coinsTags, CollectionCreateRequest request) {
        return coinsTags;
    }
}
