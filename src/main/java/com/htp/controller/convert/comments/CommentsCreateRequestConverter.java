package com.htp.controller.convert.comments;

import com.htp.controller.convert.EntityConverter;
import com.htp.controller.convert.likes.LikesRequestConverter;
import com.htp.controller.requests.comments.CommentsCreateRequest;
import com.htp.controller.requests.likes.LikesCreateRequest;
import com.htp.entity.comments.Comments;
import com.htp.entity.likes.Likes;
import com.htp.service.AllService;
import com.htp.service.collection.CollectionService;
import com.htp.service.users.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class CommentsCreateRequestConverter extends CommentsRequestConverter<CommentsCreateRequest, Comments> {

    public CommentsCreateRequestConverter(CollectionService collectionService, UsersService usersService, AllService allService) {
        super(collectionService, usersService, allService);
    }

    @Override
    public Comments convert(CommentsCreateRequest request) {
        Comments comments = new Comments();
        return doConvert(comments, request);
    }
}
