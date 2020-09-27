package com.htp.controller.convert.coins;


import com.htp.controller.requests.coins.CoinsUpdateRequest;
import com.htp.controller.requests.collection.CollectionUpdateRequest;
import com.htp.entity.coins.Coins;
import com.htp.entity.collection.Collection;
import com.htp.exceptions.EntityNotFoundException;
import com.htp.service.collection.CollectionService;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class CoinsChangeRequestConverter extends CoinsRequestConverter<CollectionUpdateRequest, Coins> {
    public CoinsChangeRequestConverter(CollectionService collectionService) {
        super(collectionService);
    }

    @Override
    public Coins convert(CollectionUpdateRequest request) {
        Coins coins =
                ofNullable(entityManager.find(Coins.class, request.getCoinsId()))
                        .orElseThrow(() -> new EntityNotFoundException(Coins.class, request.getCoinsId()));
        return doConvert(coins, request);
    }
}
