package com.htp.controller.convert.badges.badgestags;

import com.htp.controller.convert.EntityConverter;
import com.htp.controller.requests.badges.badgestags.BadgesTagsCreateRequest;
import com.htp.controller.requests.collection.CollectionCreateRequest;
import com.htp.entity.collection.Collection;
import com.htp.entity.tags.BadgesTags;
import com.htp.service.badges.BadgesService;
import com.htp.service.tags.TagsService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class BadgesTagsRequestConverter<S, T> extends EntityConverter<S, T> {

    protected BadgesTags doConvert(BadgesTags badgesTags, CollectionCreateRequest request) {
        return badgesTags;
    }
}
