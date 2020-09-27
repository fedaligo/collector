package com.htp.controller.convert.tags;

import com.htp.entity.tags.BadgesTags;
import com.htp.entity.tags.Tags;
import com.htp.service.tags.TagsService;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class TagsChangeRequestConverter extends TagsRequestConverter<String, Tags> {

    private final TagsService tagsService;

    public TagsChangeRequestConverter(TagsService tagsService, TagsService tagsService1) {
        super(tagsService);
        this.tagsService = tagsService1;
    }

    @Override
    public Tags convert(String tagsName) {
        try{
            Tags tags = tagsService.getTagsByName(tagsName);
            return doConvert(tags, tagsName);
        } catch (NullPointerException e){
            Tags tags = new Tags();
            return doConvert(tags, tagsName);
        }
    }
}
