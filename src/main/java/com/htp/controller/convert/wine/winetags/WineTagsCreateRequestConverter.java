package com.htp.controller.convert.wine.winetags;

import com.htp.controller.convert.collection.CollectionRequestConverter;
import com.htp.controller.requests.collection.CollectionCreateRequest;
import com.htp.entity.collection.Collection;
import com.htp.entity.tags.StampsTags;
import com.htp.entity.tags.WineTags;
import com.htp.service.tags.TagsService;
import com.htp.service.wine.WineService;
import org.springframework.stereotype.Component;

@Component
public class WineTagsCreateRequestConverter extends WineTagsRequestConverter<CollectionCreateRequest, WineTags> {

    @Override
    public WineTags convert(CollectionCreateRequest request) {
        WineTags wineTags = new WineTags();
        return doConvert(wineTags, request);
    }
}
