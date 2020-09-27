package com.htp.controller.convert.tags;

import com.htp.entity.tags.Tags;
import com.htp.service.tags.TagsService;
import org.springframework.stereotype.Component;

@Component
public class TagsChangeRequestConverter extends TagsRequestConverter<String, Tags> {

    public TagsChangeRequestConverter(TagsService tagsService) {
        super(tagsService);
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
