package com.htp.controller.convert.coins;

import com.htp.controller.requests.collection.CollectionCreateRequest;
import com.htp.entity.coins.Coins;
import com.htp.service.collection.CollectionService;
import org.springframework.stereotype.Component;

@Component
public class CoinsCreateRequestConverter extends CoinsRequestConverter<CollectionCreateRequest, Coins> {

    public CoinsCreateRequestConverter(CollectionService collectionService) {
        super(collectionService);
    }

    @Override
    public Coins convert(CollectionCreateRequest request) {
        Coins coins = new Coins();
        return doConvert(coins, request);
    }
}
