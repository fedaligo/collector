package com.htp.controller.convert.likes;

import com.htp.controller.convert.users.UserRequestConverter;
import com.htp.controller.requests.likes.LikesCreateRequest;
import com.htp.controller.requests.users.UserCreateRequest;
import com.htp.entity.likes.Likes;
import com.htp.entity.users.Users;
import com.htp.service.AllService;
import com.htp.service.collection.CollectionService;
import com.htp.service.users.UsersService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class LikesCreateRequestConverter extends LikesRequestConverter<LikesCreateRequest, Likes> {
    public LikesCreateRequestConverter(CollectionService collectionService, UsersService usersService, AllService allService) {
        super(collectionService, usersService, allService);
    }

    @Override
    public Likes convert(LikesCreateRequest request) {
        Likes likes = new Likes();
        return doConvert(likes, request);
    }
}
