package com.htp.controller.convert.stamps.stampstags;


import com.htp.controller.requests.collection.CollectionUpdateRequest;
import com.htp.controller.requests.stamps.stampstags.StampsTagsUpdateRequest;
import com.htp.entity.collection.Collection;
import com.htp.entity.tags.BadgesTags;
import com.htp.entity.tags.StampsTags;
import com.htp.exceptions.EntityNotFoundException;
import com.htp.service.stamps.StampsService;
import com.htp.service.tags.BadgesTagsService;
import com.htp.service.tags.StampsTagsService;
import com.htp.service.tags.TagsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
@RequiredArgsConstructor
public class StampsTagsChangeRequestConverter extends StampsTagsRequestConverter<CollectionUpdateRequest, StampsTags> {

    private final TagsService tagsService;
    private final StampsTagsService stampsTagsService;

    @Override
    public StampsTags convert(CollectionUpdateRequest request) {
        try{
            Long tagsId = tagsService.getTagsByName(request.getTagsIterate()).getId();
            StampsTags stampsTags = stampsTagsService.findStampsTagsByParameters(tagsId, request.getStampsId());
            return doConvert(stampsTags, request);
        } catch (NullPointerException e){
            StampsTags stampsTags = new StampsTags();
            return doConvert(stampsTags, request);
        }
    }
}
