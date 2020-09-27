package com.htp.service.tags;

import com.htp.entity.tags.Tags;
import com.htp.entity.tags.WineTags;
import com.htp.repository.tags.WineTagsRepository;
import com.htp.service.AllService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<String> findNameOfTagsByItemId(Long id){
        List<String> namesOfTags = new ArrayList<>();
        List<WineTags> wine = findAllWineTags();
        for(int i=0; i<wine.size(); i++){
            if(wine.get(i).getWineWineTags().getCollectionWine().getId() == id){
                namesOfTags.add(wine.get(i).getTagsWineTags().getName());
            }
        }
        return namesOfTags;
    }

    public List<Tags> findTagsByItemId(Long id){
        List<Tags> tags = new ArrayList<>();
        List<WineTags> wine = findAllWineTags();
        for(int i=0; i<wine.size(); i++){
            if(wine.get(i).getWineWineTags().getCollectionWine().getId() == id){
                tags.add(wine.get(i).getTagsWineTags());
            }
        }
        return tags;
    }


    public WineTags findWineTagsByParameters(Long tagsId, Long wineId){
        return wineTagsRepository.findWineTagsByParameters(tagsId, wineId);
    }

    public List<WineTags> findWineTagsByWineId(Long wineId){
        return wineTagsRepository.findWineTagsByWineId(wineId);
    }

    public void deleteItem(Long id){
        wineTagsRepository.deleteWineTagsById(id);
    }

    public void saveItem(WineTags wineTags){
        wineTagsRepository.saveAndFlush(wineTags);
    }

    public void updateItem(WineTags wineTags){
        wineTagsRepository.save(wineTags);
    }

}
