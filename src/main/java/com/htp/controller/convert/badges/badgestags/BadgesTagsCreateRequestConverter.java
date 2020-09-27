package com.htp.controller.convert.badges.badgestags;


import com.htp.controller.requests.badges.badgestags.BadgesTagsCreateRequest;
import com.htp.controller.requests.collection.CollectionCreateRequest;
import com.htp.entity.collection.Collection;
import com.htp.entity.tags.BadgesTags;
import com.htp.service.badges.BadgesService;
import com.htp.service.tags.TagsService;
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
