package com.htp.controller.convert.coins.coinstags;


import com.htp.controller.requests.coins.coinstags.CoinsTagsUpdateRequest;
import com.htp.controller.requests.collection.CollectionUpdateRequest;
import com.htp.entity.collection.Collection;
import com.htp.entity.tags.BadgesTags;
import com.htp.entity.tags.CoinsTags;
import com.htp.exceptions.EntityNotFoundException;
import com.htp.service.coins.CoinsService;
import com.htp.service.tags.BadgesTagsService;
import com.htp.service.tags.CoinsTagsService;
import com.htp.service.tags.TagsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
@RequiredArgsConstructor
public class CoinsTagsChangeRequestConverter extends CoinsTagsRequestConverter<CollectionUpdateRequest, CoinsTags> {

    private final TagsService tagsService;
    private final CoinsTagsService coinsTagsService;

    @Override
    public CoinsTags convert(CollectionUpdateRequest request) {
        try{
            Long tagsId = tagsService.getTagsByName(request.getTagsIterate()).getId();
            CoinsTags coinsTags =coinsTagsService.findCoinsTagsByParameters(tagsId, request.getCoinsId());
            return doConvert(coinsTags, request);
        } catch (NullPointerException e){
            CoinsTags coinsTags = new CoinsTags();
            return doConvert(coinsTags, request);
        }
    }
}
