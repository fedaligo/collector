package com.htp.controller.convert.stamps;



import com.htp.controller.requests.collection.CollectionUpdateRequest;
import com.htp.controller.requests.stamps.StampsUpdateRequest;
import com.htp.entity.collection.Collection;
import com.htp.entity.stamps.Stamps;
import com.htp.exceptions.EntityNotFoundException;
import com.htp.service.collection.CollectionService;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class StampsChangeRequestConverter extends StampsRequestConverter<CollectionUpdateRequest, Stamps> {
    public StampsChangeRequestConverter(CollectionService collectionService) {
        super(collectionService);
    }

    @Override
    public Stamps convert(CollectionUpdateRequest request) {
        Stamps stamps =
                ofNullable(entityManager.find(Stamps.class, request.getStampsId()))
                        .orElseThrow(() -> new EntityNotFoundException(Stamps.class, request.getStampsId()));
        return doConvert(stamps, request);
    }
}
