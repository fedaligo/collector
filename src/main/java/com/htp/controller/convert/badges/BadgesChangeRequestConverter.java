package com.htp.controller.convert.badges;


import com.htp.controller.requests.badges.BadgesUpdateRequest;
import com.htp.controller.requests.collection.CollectionUpdateRequest;
import com.htp.entity.badges.Badges;
import com.htp.entity.collection.Collection;
import com.htp.exceptions.EntityNotFoundException;
import com.htp.service.collection.CollectionService;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class BadgesChangeRequestConverter extends BadgesRequestConverter<CollectionUpdateRequest, Badges> {
    public BadgesChangeRequestConverter(CollectionService collectionService) {
        super(collectionService);
    }

    @Override
    public Badges convert(CollectionUpdateRequest request) {
        Badges badges =
                ofNullable(entityManager.find(Badges.class, request.getBadgesId()))
                        .orElseThrow(() -> new EntityNotFoundException(Badges.class, request.getBadgesId()));
        return doConvert(badges, request);
    }
}
