package com.htp.controller.convert.wine;


import com.htp.controller.requests.collection.CollectionUpdateRequest;
import com.htp.controller.requests.wine.WineUpdateRequest;
import com.htp.entity.collection.Collection;
import com.htp.entity.wine.Wine;
import com.htp.exceptions.EntityNotFoundException;
import com.htp.service.collection.CollectionService;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class WineChangeRequestConverter extends WineRequestConverter<CollectionUpdateRequest, Wine> {
    public WineChangeRequestConverter(CollectionService collectionService) {
        super(collectionService);
    }

    @Override
    public Wine convert(CollectionUpdateRequest request) {
        Wine wine =
                ofNullable(entityManager.find(Wine.class, request.getWineId()))
                        .orElseThrow(() -> new EntityNotFoundException(Wine.class, request.getWineId()));
        return doConvert(wine, request);
    }
}
