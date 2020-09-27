package com.htp.controller.convert.likes;

import com.htp.controller.convert.EntityConverter;
import com.htp.controller.requests.likes.LikesCreateRequest;
import com.htp.entity.collection.Collection;
import com.htp.entity.likes.Likes;
import com.htp.entity.users.Users;
import com.htp.service.AllService;
import com.htp.service.collection.CollectionService;
import com.htp.service.users.UsersService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class LikesRequestConverter<S, T> extends EntityConverter<S, T> {

    private final CollectionService collectionService;
    private final UsersService usersService;
    private final AllService allService;

    protected Likes doConvert(Likes likes, LikesCreateRequest request) {
        Users users = usersService.findByUserName(allService
                .getTokenFromHeaderAndUserNameFromToken(request.getAuthHeader())[1]);

        Collection collection = collectionService.findItemById(request.getIdCollection());

        likes.setUsersLikes(users);
        likes.setCollectionLikes(collection);
        return likes;
    }
}
