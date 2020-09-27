package com.htp.service.badges;

import com.htp.entity.badges.Badges;
import com.htp.entity.collection.Collection;
import com.htp.entity.tags.Tags;
import com.htp.repository.badges.BadgesRepository;
import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BadgesService {
    private final BadgesRepository badgesRepository;

    public Badges findBadgesById(Long id){
        return badgesRepository.findById(id).orElse(null);
    }

    public void deleteItem(Long id){
        badgesRepository.deleteBadgesById(id);
    }

    public void saveItem(Badges badges){
        badgesRepository.saveAndFlush(badges);
    }

    public void updateItem(Badges badges){
        badgesRepository.save(badges);
    }

}
