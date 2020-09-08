package com.htp.service.tags;

import com.htp.entity.tags.BadgesTags;
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
}
