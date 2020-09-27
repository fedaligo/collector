package com.htp.controller.convert.stamps;

import com.htp.controller.convert.EntityConverter;
import com.htp.controller.requests.collection.CollectionCreateRequest;
import com.htp.entity.stamps.Stamps;
import com.htp.service.collection.CollectionService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class StampsRequestConverter<S, T> extends EntityConverter<S, T> {

    private final CollectionService collectionService;

    protected Stamps doConvert(Stamps stamps, CollectionCreateRequest request) {
        stamps.setPerforation(setPerforation(request.getPerforation()));
        stamps.setColor(request.getColor());
        stamps.setValue(request.getValue());
        stamps.setCollectionStamps(collectionService.findItemById(request.getIdCollection()));
        return stamps;
    }

    private boolean setPerforation(String perforationText){
        boolean perforation = false;
        if(perforationText.equals("true"))
            perforation = true;
        return perforation;
    }
}


