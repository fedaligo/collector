package com.htp.service.likes;

import com.htp.entity.comments.Comments;
import com.htp.entity.likes.Likes;
import com.htp.repository.likes.LikesRepository;
import com.htp.service.AllService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class LikesService {

    private final LikesRepository likesRepository;
    private final AllService allService;

    private List<Likes> findAllLikes(){
        return likesRepository.findAll();
    }

    public boolean isItemHasLikeFromCurrentUser(Long itemId, String authorizationHeader){
        List<Likes> alllikes = findAllLikes();
        for(int i=0; i<alllikes.size(); i++){
            if(areTheSameUsersNames(alllikes, i, authorizationHeader) && areTheSameItemsId(itemId, alllikes, i)){
                return true;
            }
        }
        return false;
    }

    public void addLikeForThisItemFromCurrentUser(Likes likes){
        likesRepository.saveAndFlush(likes);
    }

    public void deleteLikeForThisItemFromCurrentUser(Long itemId, String authorizationHeader){
        List<Likes> alllikes = findAllLikes();
        for(int i=0; i<alllikes.size(); i++){
            if(areTheSameUsersNames(alllikes, i, authorizationHeader) && areTheSameItemsId(itemId, alllikes, i)){
                likesRepository.deleteLikesById(alllikes.get(i).getId());
            }
        }
    }

    private boolean areTheSameUsersNames(List<Likes> allLikes, int i, String authorizationHeader){
        return allService.getTokenFromHeaderAndUserNameFromToken(authorizationHeader)[1]
                .equals(allLikes.get(i).getUsersLikes().getUsername());
    }

    private boolean areTheSameItemsId(Long itemId, List<Likes> allLikes, int i){
        return allLikes.get(i).getCollectionLikes().getId()==itemId;
    }
}
