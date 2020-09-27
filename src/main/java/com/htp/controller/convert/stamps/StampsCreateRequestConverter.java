package com.htp.controller.convert.stamps;


import com.htp.controller.requests.collection.CollectionCreateRequest;
import com.htp.controller.requests.stamps.StampsCreateRequest;
import com.htp.entity.collection.Collection;
import com.htp.entity.stamps.Stamps;
import com.htp.entity.tags.StampsTags;
import com.htp.service.collection.CollectionService;
import org.springframework.stereotype.Component;

@Component
public class StampsCreateRequestConverter extends StampsRequestConverter<CollectionCreateRequest, Stamps> {
    public StampsCreateRequestConverter(CollectionService collectionService) {
        super(collectionService);
    }

    @Override
    public Stamps convert(CollectionCreateRequest request) {
        Stamps stamps = new Stamps();
        return doConvert(stamps, request);
    }
}
