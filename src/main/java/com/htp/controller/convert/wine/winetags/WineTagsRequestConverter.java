package com.htp.controller.convert.wine.winetags;

import com.htp.controller.convert.EntityConverter;
import com.htp.controller.requests.collection.CollectionCreateRequest;
import com.htp.entity.tags.WineTags;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class WineTagsRequestConverter<S, T> extends EntityConverter<S, T> {
    protected WineTags doConvert(WineTags wineTags, CollectionCreateRequest request) {
        return wineTags;
    }
}
