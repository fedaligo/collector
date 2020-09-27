package com.htp.controller.convert.stamps.stampstags;


import com.htp.controller.requests.collection.CollectionCreateRequest;
import com.htp.controller.requests.stamps.stampstags.StampsTagsCreateRequest;
import com.htp.entity.collection.Collection;
import com.htp.entity.tags.CoinsTags;
import com.htp.entity.tags.StampsTags;
import com.htp.service.stamps.StampsService;
import com.htp.service.tags.TagsService;
import org.springframework.stereotype.Component;

@Component
public class StampsTagsCreateRequestConverter extends StampsTagsRequestConverter<CollectionCreateRequest, StampsTags> {

    @Override
    public StampsTags convert(CollectionCreateRequest request) {
        StampsTags stampsTags = new StampsTags();
        return doConvert(stampsTags, request);
    }
}
