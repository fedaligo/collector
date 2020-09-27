package com.htp.controller.convert.wine.winetags;

import com.htp.controller.requests.collection.CollectionCreateRequest;
import com.htp.entity.tags.WineTags;
import org.springframework.stereotype.Component;

@Component
public class WineTagsCreateRequestConverter extends WineTagsRequestConverter<CollectionCreateRequest, WineTags> {

    @Override
    public WineTags convert(CollectionCreateRequest request) {
        WineTags wineTags = new WineTags();
        return doConvert(wineTags, request);
    }
}
