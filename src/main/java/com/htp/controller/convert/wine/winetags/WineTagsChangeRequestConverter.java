package com.htp.controller.convert.wine.winetags;

import com.htp.controller.requests.collection.CollectionUpdateRequest;
import com.htp.entity.tags.WineTags;
import com.htp.service.tags.TagsService;
import com.htp.service.tags.WineTagsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WineTagsChangeRequestConverter extends WineTagsRequestConverter<CollectionUpdateRequest, WineTags> {

    private final TagsService tagsService;
    private final WineTagsService wineTagsService;

    @Override
    public WineTags convert(CollectionUpdateRequest request) {
        try{
            Long tagsId = tagsService.getTagsByName(request.getTagsIterate()).getId();
            WineTags wineTags = wineTagsService.findWineTagsByParameters(tagsId, request.getWineId());
            return doConvert(wineTags, request);
        } catch (NullPointerException e){
            WineTags wineTags = new WineTags();
            return doConvert(wineTags, request);
        }
    }
}
