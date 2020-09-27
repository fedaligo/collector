package com.htp.controller.convert.badges.badgestags;

import com.htp.controller.requests.collection.CollectionUpdateRequest;
import com.htp.entity.tags.BadgesTags;
import com.htp.exceptions.EntityNotFoundException;
import com.htp.service.badges.BadgesService;
import com.htp.service.tags.BadgesTagsService;
import com.htp.service.tags.TagsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Null;

import static java.util.Optional.ofNullable;

@Component
@RequiredArgsConstructor
public class BadgesTagsChangeRequestConverter extends BadgesTagsRequestConverter<CollectionUpdateRequest, BadgesTags> {

    private final TagsService tagsService;
    private final BadgesTagsService badgesTagsService;

    @Override
    public BadgesTags convert(CollectionUpdateRequest request) {
        try{
            Long tagsId = tagsService.getTagsByName(request.getTagsIterate()).getId();
            BadgesTags badgesTags =badgesTagsService.findBadgesTagsByParameters(tagsId, request.getBadgesId());
            return doConvert(badgesTags, request);
        } catch (NullPointerException e){
            BadgesTags badgesTags = new BadgesTags();
            return doConvert(badgesTags, request);
        }
    }
}
