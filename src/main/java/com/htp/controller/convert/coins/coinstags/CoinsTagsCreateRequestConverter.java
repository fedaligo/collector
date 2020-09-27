package com.htp.controller.convert.coins.coinstags;


import com.htp.controller.requests.coins.coinstags.CoinsTagsCreateRequest;
import com.htp.controller.requests.collection.CollectionCreateRequest;
import com.htp.entity.collection.Collection;
import com.htp.entity.tags.BooksTags;
import com.htp.entity.tags.CoinsTags;
import com.htp.service.coins.CoinsService;
import com.htp.service.tags.TagsService;
import org.springframework.stereotype.Component;

@Component
public class CoinsTagsCreateRequestConverter extends CoinsTagsRequestConverter<CollectionCreateRequest, CoinsTags> {

    @Override
    public CoinsTags convert(CollectionCreateRequest request) {
        CoinsTags coinsTags = new CoinsTags();
        return doConvert(coinsTags, request);
    }
}
