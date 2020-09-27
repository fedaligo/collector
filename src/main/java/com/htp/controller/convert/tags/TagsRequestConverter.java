package com.htp.controller.convert.tags;

import com.htp.controller.convert.EntityConverter;
import com.htp.entity.tags.Tags;
import com.htp.service.tags.TagsService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public abstract class TagsRequestConverter<S, T> extends EntityConverter<S, T> {

    protected final TagsService tagsService;

    protected Tags doConvert(Tags tags, String tagsName) {
        List<Tags> allTags = tagsService.getAllTags();
        for(int p = 0; p<allTags.size(); p++){
            if(tagsName.equals(allTags.get(p).getName())){
                tags = tagsService.getTagsByName(allTags.get(p).getName());
                break;
            }
            if(p == allTags.size()-1){
                tags.setName(tagsName);
            }
        }
        return tags;
    }


}
