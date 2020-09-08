package com.htp.service.tags;

import com.htp.entity.tags.WineTags;
import com.htp.repository.tags.WineTagsRepository;
import com.htp.service.AllService;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class WineTagsService {
    private static final int CONTINUE = 0;
    private static final int NEXT = 1;
    private final AllService allService;
    private final WineTagsRepository wineTagsRepository;

    public List<WineTags> findAllWineTags() {
        return wineTagsRepository.findAll();
    }

    public List<Long> getIdForCollectionFromWineTagsByWord(String word) {
        List<WineTags> wine = findAllWineTags();
        List<Long> saveIdForMappingByCollection = new ArrayList();
        for(int i = 0; i < wine.size(); ++i) {
            long idValue = wine.get(i).getWineWineTags().getCollectionWine().getId();
            if (findWordInWineAndGetBreakValue(wine, word, i, saveIdForMappingByCollection, idValue) != NEXT) {
                saveIdForMappingByCollection.add(idValue);
            }
        }

        return saveIdForMappingByCollection;
    }

    private int findWordInWineAndGetBreakValue(List<WineTags> wine, String word, int i,
                                               List<Long> saveIdForMappingByCollection, long idValue) {
        int breakValue = CONTINUE;
        String info = wine.get(i).toString();
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
