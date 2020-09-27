package com.htp.controller.convert.badges.badgestags;

import com.htp.controller.convert.EntityConverter;
import com.htp.controller.requests.collection.CollectionCreateRequest;
import com.htp.entity.tags.BadgesTags;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class BadgesTagsRequestConverter<S, T> extends EntityConverter<S, T> {

    protected BadgesTags doConvert(BadgesTags badgesTags, CollectionCreateRequest request) {
        return badgesTags;
    }
}
