package com.htp.service.tags;

import com.htp.entity.tags.StampsTags;
import com.htp.repository.tags.StampsTagsRepository;
import com.htp.service.AllService;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class StampsTagsService {
    private static final int CONTINUE = 0;
    private static final int NEXT = 1;
    private final AllService allService;
    private final StampsTagsRepository stampsTagsRepository;

    public List<StampsTags> findAllStampsTags() {
        return stampsTagsRepository.findAll();
    }

    public List<Long> getIdForCollectionFromStampsTagsByWord(String word) {
        List<StampsTags> stamps = findAllStampsTags();
        List<Long> saveIdForMappingByCollection = new ArrayList();
        for(int i = 0; i < stamps.size(); ++i) {
            long idValue = stamps.get(i).getStampsStampsTags().getCollectionStamps().getId();
            if (findWordInStampsAndGetBreakValue(stamps, word, i, saveIdForMappingByCollection, idValue) != NEXT) {
                saveIdForMappingByCollection.add(idValue);
            }
        }
        return saveIdForMappingByCollection;
    }

    private int findWordInStampsAndGetBreakValue(List<StampsTags> stamps, String word, int i, List<Long> saveIdForMappingByCollection, long idValue) {
        int breakValue = CONTINUE;
        String info = stamps.get(i).toString();
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
