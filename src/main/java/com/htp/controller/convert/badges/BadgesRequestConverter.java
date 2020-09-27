package com.htp.controller.convert.badges;

import com.htp.controller.convert.EntityConverter;
import com.htp.controller.requests.collection.CollectionCreateRequest;
import com.htp.entity.badges.Badges;
import com.htp.service.collection.CollectionService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class BadgesRequestConverter<S, T> extends EntityConverter<S, T> {

    private final CollectionService collectionService;

    protected Badges doConvert(Badges badges, CollectionCreateRequest request) {
        badges.setMaterial(request.getMaterial());
        badges.setKind(request.getBadgesKind());
        badges.setFastening(request.getFastening());
        badges.setCollectionBadges(collectionService.findItemById(request.getIdCollection()));
        return badges;
    }
}
