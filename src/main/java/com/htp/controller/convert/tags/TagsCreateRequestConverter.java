package com.htp.controller.convert.tags;

import com.htp.entity.tags.Tags;
import com.htp.service.tags.TagsService;
import org.springframework.stereotype.Component;

@Component
public class TagsCreateRequestConverter extends TagsRequestConverter<String, Tags> {

    public TagsCreateRequestConverter(TagsService tagsService) {
        super(tagsService);
    }

    @Override
    public Tags convert(String tagsName) {
        Tags tags = new Tags();
        return doConvert(tags, tagsName);
    }
}
