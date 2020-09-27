package com.htp.controller.convert.stamps.stampstags;

import com.htp.controller.convert.EntityConverter;
import com.htp.controller.requests.collection.CollectionCreateRequest;
import com.htp.entity.tags.StampsTags;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class StampsTagsRequestConverter<S, T> extends EntityConverter<S, T> {
    protected StampsTags doConvert(StampsTags stampsTags, CollectionCreateRequest request) {
        return stampsTags;
    }
}
