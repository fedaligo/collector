package com.htp.controller.convert.likes;

import com.htp.controller.requests.likes.LikesCreateRequest;
import com.htp.controller.requests.likes.LikesUpdateRequest;
import com.htp.entity.likes.Likes;
import com.htp.entity.users.Users;
import com.htp.exceptions.EntityNotFoundException;
import com.htp.service.AllService;
import com.htp.service.collection.CollectionService;
import com.htp.service.users.UsersService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class LikesChangeRequestConverter extends LikesRequestConverter<LikesUpdateRequest, Likes>{
    public LikesChangeRequestConverter(CollectionService collectionService, UsersService usersService, AllService allService) {
        super(collectionService, usersService, allService);
    }

    @Override
    public Likes convert(LikesUpdateRequest request) {
        Likes likes =
                ofNullable(entityManager.find(Likes.class, request.getLikesId()))
                        .orElseThrow(() -> new EntityNotFoundException(Likes.class, request.getLikesId()));
        return doConvert(likes, request);
    }
}
