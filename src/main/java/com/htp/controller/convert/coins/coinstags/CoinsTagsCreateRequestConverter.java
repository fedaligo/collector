package com.htp.controller.convert.coins.coinstags;


import com.htp.controller.requests.collection.CollectionCreateRequest;
import com.htp.entity.tags.CoinsTags;
import org.springframework.stereotype.Component;

@Component
public class CoinsTagsCreateRequestConverter extends CoinsTagsRequestConverter<CollectionCreateRequest, CoinsTags> {

    @Override
    public CoinsTags convert(CollectionCreateRequest request) {
        CoinsTags coinsTags = new CoinsTags();
        return doConvert(coinsTags, request);
    }
}
