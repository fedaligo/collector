package com.htp.controller.convert.stamps.stampstags;


import com.htp.controller.requests.collection.CollectionCreateRequest;
import com.htp.entity.tags.StampsTags;
import org.springframework.stereotype.Component;

@Component
public class StampsTagsCreateRequestConverter extends StampsTagsRequestConverter<CollectionCreateRequest, StampsTags> {

    @Override
    public StampsTags convert(CollectionCreateRequest request) {
        StampsTags stampsTags = new StampsTags();
        return doConvert(stampsTags, request);
    }
}
