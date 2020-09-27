package com.htp.service.comments;

import com.htp.entity.comments.Comments;
import com.htp.repository.comments.CommentsRepository;
import com.htp.security.util.JwtUtil;
import com.htp.service.AllService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class CommentsService {

    private final CommentsRepository commentsRepository;
    private final AllService allService;
    private final JwtUtil jwtUtil;

    public List<Comments> findAllComments(){
        return commentsRepository.findAll();
    }

    public List<Comments> findCommentsByItemId(Long itemId){
        List<Comments> allComments = findAllComments();
        List<Comments> allCommentsByItemId = new ArrayList<>();
        for(int i=0; i<allComments.size(); i++){
            if(areTheSameItemsId(itemId, allComments, i)){
                allCommentsByItemId.add(allComments.get(i));
            }
        }
        return allCommentsByItemId;
    }

    public List<String> findCommentsUsersNamesByItemId(Long itemId){
        List<String> allCommentsUsersNamesByItemId = new ArrayList<>();
        List<Comments> allComments = findAllComments();
        for(int i=0; i<allComments.size(); i++){
            if(allComments.get(i).getCollectionComments().getId() == itemId){
                allCommentsUsersNamesByItemId.add(allComments.get(i).getUsersComments().getUsername());
            }
        }
        return allCommentsUsersNamesByItemId;
    }

    public void addCommentForThisItemFromCurrentUser(Comments comments){
        commentsRepository.saveAndFlush(comments);
    }

    public void deleteCommentForThisItemFromCurrentUser(Long commentId){
                commentsRepository.deleteCommentsById(commentId);
    }

    private boolean areTheSameItemsId(Long itemId, List<Comments> allComments, int i){
        return allComments.get(i).getCollectionComments().getId()==itemId;
    }
}
