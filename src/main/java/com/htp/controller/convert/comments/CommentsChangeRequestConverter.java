package com.htp.controller.convert.comments;

import com.htp.controller.requests.comments.CommentsUpdateRequest;
import com.htp.entity.comments.Comments;
import com.htp.exceptions.EntityNotFoundException;
import com.htp.service.AllService;
import com.htp.service.collection.CollectionService;
import com.htp.service.users.UsersService;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class CommentsChangeRequestConverter extends CommentsRequestConverter<CommentsUpdateRequest, Comments> {
    public CommentsChangeRequestConverter(CollectionService collectionService, UsersService usersService, AllService allService) {
        super(collectionService, usersService, allService);
    }

    @Override
    public Comments convert(CommentsUpdateRequest request) {
        Comments comments =
                ofNullable(entityManager.find(Comments.class, request.getCommentsId()))
                        .orElseThrow(() -> new EntityNotFoundException(Comments.class, request.getCommentsId()));
        return doConvert(comments, request);
    }
}
