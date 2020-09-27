package com.htp.controller.convert.collection;

import com.htp.controller.requests.collection.CollectionCreateRequest;
import com.htp.entity.collection.Collection;
import com.htp.service.users.UsersService;
import org.springframework.stereotype.Component;

@Component
public class CollectionCreateRequestConverter extends CollectionRequestConverter<CollectionCreateRequest, Collection> {

    public CollectionCreateRequestConverter(UsersService usersService) {
        super(usersService);
    }

    @Override
    public Collection convert(CollectionCreateRequest request) {
        Collection collection = new Collection();
        return doConvert(collection, request);
    }
}
