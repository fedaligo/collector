package com.htp.controller.convert.wine.winetags;

import com.htp.controller.convert.collection.CollectionRequestConverter;
import com.htp.controller.requests.collection.CollectionUpdateRequest;
import com.htp.controller.requests.wine.winetags.WineTagsUpdateRequest;
import com.htp.entity.collection.Collection;
import com.htp.entity.tags.BadgesTags;
import com.htp.entity.tags.WineTags;
import com.htp.exceptions.EntityNotFoundException;
import com.htp.service.tags.BadgesTagsService;
import com.htp.service.tags.TagsService;
import com.htp.service.tags.WineTagsService;
import com.htp.service.wine.WineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

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
