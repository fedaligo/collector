package com.htp.controller.convert.collection;

import com.htp.controller.requests.collection.CollectionUpdateRequest;
import com.htp.entity.collection.Collection;
import com.htp.exceptions.EntityNotFoundException;
import com.htp.service.users.UsersService;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class CollectionChangeRequestConverter extends CollectionRequestConverter<CollectionUpdateRequest, Collection> {

    public CollectionChangeRequestConverter(UsersService usersService) {
        super(usersService);
    }

    @Override
    public Collection convert(CollectionUpdateRequest request) {
        Collection collection =
                ofNullable(entityManager.find(Collection.class, request.getCollectionId()))
                        .orElseThrow(() -> new EntityNotFoundException(Collection.class, request.getCollectionId()));
        return doConvert(collection, request);
    }
}
