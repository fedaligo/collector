package com.htp.service.tags;

import com.htp.entity.tags.BadgesTags;
import com.htp.entity.tags.Tags;
import com.htp.entity.wine.Wine;
import com.htp.repository.tags.BadgesTagsRepository;
import com.htp.service.AllService;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BadgesTagsService {
    private static final int CONTINUE = 0;
    private static final int NEXT = 1;
    private final AllService allService;
    private final BadgesTagsRepository badgesTagsRepository;

    public List<BadgesTags> findAllBadgesTags() {
        return badgesTagsRepository.findAll();
    }

    public List<Long> getIdForCollectionFromBadgesTagsByWord(String word) {
        List<BadgesTags> badges = findAllBadgesTags();
        List<Long> saveIdForMappingByCollection = new ArrayList();
        for(int i = 0; i < badges.size(); ++i) {
            long idValue = badges.get(i).getBadgesBadgesTags().getCollectionBadges().getId();
            if (findWordInBadgesAndGetBreakValue(badges, word, i, saveIdForMappingByCollection, idValue) != NEXT) {
                saveIdForMappingByCollection.add(idValue);
            }
        }
        return saveIdForMappingByCollection;
    }

    private int findWordInBadgesAndGetBreakValue(List<BadgesTags> badges, String word, int i,
                                                 List<Long> saveIdForMappingByCollection, long idValue) {
        int breakValue = CONTINUE;
        String info = badges.get(i).toString();
        info = allService.getOnlyInfoFromString(info);
        if (info.contains(word)) {
            if (saveIdForMappingByCollection.size() > 0) {
                breakValue = allService.checkIdIsNotRepeated(saveIdForMappingByCollection, idValue);
            }
        } else {
            breakValue = NEXT;
        }
        return breakValue;
    }

    public List<String> findNameOfTagsByItemId(Long id){
        List<String> namesOfTags = new ArrayList<>();
        List<BadgesTags> badges = findAllBadgesTags();
        for(int i=0; i<badges.size(); i++){
            if(badges.get(i).getBadgesBadgesTags().getCollectionBadges().getId() == id){
                namesOfTags.add(badges.get(i).getTagsBadgesTags().getName());
            }
        }
        return namesOfTags;
    }

    public List<Tags> findTagsByItemId(Long id){
        List<Tags> tags = new ArrayList<>();
        List<BadgesTags> badges = findAllBadgesTags();
        for(int i=0; i<badges.size(); i++){
            if(badges.get(i).getBadgesBadgesTags().getCollectionBadges().getId() == id){
                tags.add(badges.get(i).getTagsBadgesTags());
            }
        }
        return tags;
    }

    public BadgesTags findBadgesTagsByParameters(Long tagsId, Long badgesId){
        return badgesTagsRepository.findBadgesTagsByParameters(tagsId, badgesId);
    }

    public List<BadgesTags> findBadgesTagsByBadgesId(Long badgesId){
        return badgesTagsRepository.findBadgesTagsByBadgesId(badgesId);
    }

    public void deleteItem(Long id){
        badgesTagsRepository.deleteBadgesTagsById(id);
    }

    public void saveItem(BadgesTags badgesTags){
        badgesTagsRepository.saveAndFlush(badgesTags);
    }

    public void updateItem(BadgesTags badgesTags){
        badgesTagsRepository.save(badgesTags);
    }
}
