package com.htp.controller.convert.badges;


import com.htp.controller.requests.collection.CollectionCreateRequest;
import com.htp.entity.badges.Badges;
import com.htp.service.collection.CollectionService;
import org.springframework.stereotype.Component;

@Component
public class BadgesCreateRequestConverter extends BadgesRequestConverter<CollectionCreateRequest, Badges> {
    public BadgesCreateRequestConverter(CollectionService collectionService) {
        super(collectionService);
    }

    @Override
    public Badges convert(CollectionCreateRequest request) {
        Badges badges = new Badges();
        return doConvert(badges, request);
    }
}
