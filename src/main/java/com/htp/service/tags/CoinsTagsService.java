package com.htp.service.tags;

import com.htp.entity.tags.CoinsTags;
import com.htp.entity.tags.Tags;
import com.htp.repository.tags.CoinsTagsRepository;
import com.htp.service.AllService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class CoinsTagsService {
    private static final int CONTINUE = 0;
    private static final int NEXT = 1;
    private final AllService allService;
    private final CoinsTagsRepository coinsTagsRepository;

    public List<CoinsTags> findAllCoinsTags() {
        return coinsTagsRepository.findAll();
    }

    public List<Long> getIdForCollectionFromCoinsTagsByWord(String word) {
        List<CoinsTags> coins = findAllCoinsTags();
        List<Long> saveIdForMappingByCollection = new ArrayList();
        for(int i = 0; i < coins.size(); ++i) {
            long idValue = coins.get(i).getCoinsCoinsTags().getCollectionCoins().getId();
            if (findWordInCoinsAndGetBreakValue(coins, word, i, saveIdForMappingByCollection, idValue) != NEXT) {
                saveIdForMappingByCollection.add(idValue);
            }
        }
        return saveIdForMappingByCollection;
    }

    private int findWordInCoinsAndGetBreakValue(List<CoinsTags> coins, String word, int i, List<Long> saveIdForMappingByCollection, long idValue) {
        int breakValue = CONTINUE;
        String info = coins.get(i).toString();
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
        List<CoinsTags> coins = findAllCoinsTags();
        for(int i=0; i<coins.size(); i++){
            if(coins.get(i).getCoinsCoinsTags().getCollectionCoins().getId() == id){
                namesOfTags.add(coins.get(i).getTagsCoinsTags().getName());
            }
        }
        return namesOfTags;
    }

    public List<Tags> findTagsByItemId(Long id){
        List<Tags> tags = new ArrayList<>();
        List<CoinsTags> coins = findAllCoinsTags();
        for(int i=0; i<coins.size(); i++){
            if(coins.get(i).getCoinsCoinsTags().getCollectionCoins().getId() == id){
                tags.add(coins.get(i).getTagsCoinsTags());
            }
        }
        return tags;
    }

    public CoinsTags findCoinsTagsByParameters(Long tagsId, Long coinsId){
        return coinsTagsRepository.findCoinsTagsByParameters(tagsId, coinsId);
    }

    public List<CoinsTags> findCoinsTagsByCoinsId(Long coinsId){
        return coinsTagsRepository.findCoinsTagsByCoinsId(coinsId);
    }

    public void deleteItem(Long id){
        coinsTagsRepository.deleteCoinsTagsById(id);
    }

    public void saveItem(CoinsTags coinsTags){
        coinsTagsRepository.saveAndFlush(coinsTags);
    }

    public void updateItem(CoinsTags coinsTags){
        coinsTagsRepository.save(coinsTags);
    }

}
