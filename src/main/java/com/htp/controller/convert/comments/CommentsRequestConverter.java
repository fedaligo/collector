package com.htp.controller.convert.comments;

import com.htp.controller.convert.EntityConverter;
import com.htp.controller.requests.comments.CommentsCreateRequest;
import com.htp.controller.requests.likes.LikesCreateRequest;
import com.htp.entity.collection.Collection;
import com.htp.entity.comments.Comments;
import com.htp.entity.likes.Likes;
import com.htp.entity.users.Users;
import com.htp.service.AllService;
import com.htp.service.collection.CollectionService;
import com.htp.service.users.UsersService;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@RequiredArgsConstructor
public abstract class CommentsRequestConverter<S, T> extends EntityConverter<S, T> {

    private final CollectionService collectionService;
    private final UsersService usersService;
    private final AllService allService;

    protected Comments doConvert(Comments comments, CommentsCreateRequest request) {

        Users users = usersService.findByUserName(allService
                .getTokenFromHeaderAndUserNameFromToken(request.getAuthHeader())[1]);

        Collection collection = collectionService.findItemById(request.getIdCollection());
        comments.setCollectionComments(collection);
        comments.setUsersComments(users);
        comments.setComment(request.getComment());
        comments.setCreated(new Timestamp(new Date().getTime()));
        return comments;
    }
}
