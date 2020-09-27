package com.htp.controller.convert.wine;

import com.htp.controller.convert.EntityConverter;
import com.htp.controller.requests.collection.CollectionCreateRequest;
import com.htp.entity.wine.Wine;
import com.htp.service.collection.CollectionService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class WineRequestConverter<S, T> extends EntityConverter<S, T> {

    private final CollectionService collectionService;

    protected Wine doConvert(Wine wine, CollectionCreateRequest request) {
        wine.setAlcohol(request.getAlcohol());
        wine.setSugar(request.getSugar());
        wine.setKind(request.getWineKind());
        wine.setCollectionWine(collectionService.findItemById(request.getIdCollection()));
        return wine;
    }
}
