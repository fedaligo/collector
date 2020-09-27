package com.htp.controller.convert.stamps.stampstags;

import com.htp.controller.convert.EntityConverter;
import com.htp.controller.requests.coins.coinstags.CoinsTagsCreateRequest;
import com.htp.controller.requests.collection.CollectionCreateRequest;
import com.htp.controller.requests.stamps.stampstags.StampsTagsCreateRequest;
import com.htp.entity.tags.CoinsTags;
import com.htp.entity.tags.StampsTags;
import com.htp.service.badges.BadgesService;
import com.htp.service.stamps.StampsService;
import com.htp.service.tags.TagsService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class StampsTagsRequestConverter<S, T> extends EntityConverter<S, T> {
    protected StampsTags doConvert(StampsTags stampsTags, CollectionCreateRequest request) {
        return stampsTags;
    }
}
