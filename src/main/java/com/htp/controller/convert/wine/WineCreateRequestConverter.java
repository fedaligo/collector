package com.htp.controller.convert.wine;

import com.htp.controller.requests.collection.CollectionCreateRequest;
import com.htp.entity.wine.Wine;
import com.htp.service.collection.CollectionService;
import org.springframework.stereotype.Component;

@Component
public class WineCreateRequestConverter extends WineRequestConverter<CollectionCreateRequest, Wine> {

    public WineCreateRequestConverter(CollectionService collectionService) {
        super(collectionService);
    }

    @Override
    public Wine convert(CollectionCreateRequest request) {
        Wine wine = new Wine();
        return doConvert(wine, request);
    }
}
