package com.htp.controller.convert.badges.badgestags;


import com.htp.controller.requests.collection.CollectionCreateRequest;
import com.htp.entity.tags.BadgesTags;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BadgesTagsCreateRequestConverter extends BadgesTagsRequestConverter<CollectionCreateRequest, BadgesTags> {

    @Override
    public BadgesTags convert(CollectionCreateRequest request) {
        BadgesTags badgesTags = new BadgesTags();
        return doConvert(badgesTags, request);
    }
}
