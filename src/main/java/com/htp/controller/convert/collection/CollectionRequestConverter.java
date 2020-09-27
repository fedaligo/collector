package com.htp.controller.convert.collection;

import com.htp.controller.convert.EntityConverter;
import com.htp.controller.requests.collection.CollectionCreateRequest;
import com.htp.entity.collection.Collection;
import com.htp.service.tags.TagsService;
import com.htp.service.users.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public abstract class CollectionRequestConverter<S, T> extends EntityConverter<S, T> {
    private final UsersService usersService;

    public Collection doConvert(Collection collection, CollectionCreateRequest request) {
        collection.setName(request.getName());
        collection.setTopic(request.getTopic());
        collection.setCost(request.getCost());
        collection.setInfo(request.getInfo());
        collection.setCountry(request.getCountry());
        collection.setRelease(request.getRelease());
        collection.setPicture(request.getPicture());
        collection.setUserCollection(usersService.findByUserName(request.getUserName()));
        return collection;
    }
}
