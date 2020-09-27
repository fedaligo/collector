package com.htp.controller.convert.coins;

import com.htp.controller.convert.EntityConverter;
import com.htp.controller.requests.collection.CollectionCreateRequest;
import com.htp.entity.coins.Coins;
import com.htp.service.collection.CollectionService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class CoinsRequestConverter<S, T> extends EntityConverter<S, T> {

    private final CollectionService collectionService;

    protected Coins doConvert(Coins coins, CollectionCreateRequest request) {
        coins.setKind(request.getCoinsKind());
        coins.setMetal(request.getMetal());
        coins.setSize(request.getSize());
        coins.setCollectionCoins(collectionService.findItemById(request.getIdCollection()));
        return coins;
    }
}
