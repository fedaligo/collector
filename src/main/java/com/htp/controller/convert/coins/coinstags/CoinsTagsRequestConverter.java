package com.htp.controller.convert.coins.coinstags;

import com.htp.controller.convert.EntityConverter;
import com.htp.controller.requests.collection.CollectionCreateRequest;
import com.htp.entity.tags.CoinsTags;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class CoinsTagsRequestConverter<S, T> extends EntityConverter<S, T> {
    protected CoinsTags doConvert(CoinsTags coinsTags, CollectionCreateRequest request) {
        return coinsTags;
    }
}
