package com.htp.service.collection;

import com.htp.entity.collection.Collection;
import com.htp.repository.collection.CollectionRepository;
import com.htp.service.tags.BadgesTagsService;
import com.htp.service.tags.BooksTagsService;
import com.htp.service.tags.CoinsTagsService;
import com.htp.service.tags.StampsTagsService;
import com.htp.service.tags.WineTagsService;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CollectionService {
    private static final int ID = 0;
    private static final int TOPIC = 1;
    private static final int SIZE_OF_COLLECTION = 2;
    private static final int LOGIN = 3;
    private static final int COUNT_OF_ITEMS_IN_COLLECTION = 4;
    private static final int CONTINUE = 0;
    private static final int NEXT = 1;
    private static final int COUNT_OF_GREATEST_COLLECTIONS = 3;
    private static final int COUNT_OF_LAST_ITEMS = 3;

    private final CollectionRepository collectionRepository;
    private final BadgesTagsService badgesTagsService;
    private final BooksTagsService booksTagsService;
    private final CoinsTagsService coinsTagsService;
    private final StampsTagsService stampsTagsService;
    private final WineTagsService wineTagsService;

    public List<Collection> findAllItems() {
        return collectionRepository.findAll();
    }

    public Collection findItemById(Long id) {
        return collectionRepository.findByIdNumber(id);
    }

    private List<String[]> getSizeOfEveryCollection() {
        List<Collection> allItems = findAllItems();
        List<String[]> usersCollectionSize = new ArrayList();

        for(int i = 0; i < allItems.size(); ++i) {
            int breakValue = checkTheSizeOfCollectionAndSetBreakValue(allItems, usersCollectionSize, i);
            if (breakValue != NEXT) {
                compareCollectionsAndAddMaxSizeOfCollection(allItems, usersCollectionSize, i);
            }
        }
        return usersCollectionSize;
    }

    private void compareCollectionsAndAddMaxSizeOfCollection(List<Collection> allItems,
                                                             List<String[]> usersCollectionSize, int i) {
        String[] maxSizeOfCollection = new String[COUNT_OF_ITEMS_IN_COLLECTION];
        for(int p = i; p < allItems.size(); ++p) {
            maxSizeOfCollection = checkAreTheSameItemsOfOneListByIdAndTopic(allItems, maxSizeOfCollection, i, p);
            addMaxSizeCollectionINtoTheList(allItems, usersCollectionSize, maxSizeOfCollection, p);
        }

    }

    private int checkTheSizeOfCollectionAndSetBreakValue(List<Collection> allItems,
                                                         List<String[]> usersCollectionSize, int i) {
        return usersCollectionSize.size() > 0 ?
                checkAreTheSameItemsIntoTwoDifferentListsByIdAndTopic(allItems, usersCollectionSize, i) : 0;
    }

    private void addMaxSizeCollectionINtoTheList(List<Collection> allItems, List<String[]> usersCollectionSize,
                                                 String[] maxSizeOfCollection, int p) {
        if (p == allItems.size() - 1) {
            usersCollectionSize.add(maxSizeOfCollection);
        }
    }

    private String[] checkAreTheSameItemsOfOneListByIdAndTopic(List<Collection> allItems,
                                                               String[] maxSizeOfCollection, int i, int p) {
        return allItems.get(i).getUserCollection().getId() == allItems.get(p).getUserCollection().getId() &&
                allItems.get(i).getTopic().equals(allItems.get(p).getTopic()) ?
                setMaxSizeCollection(allItems, maxSizeOfCollection, p, i) : maxSizeOfCollection;
    }

    private int checkAreTheSameItemsIntoTwoDifferentListsByIdAndTopic(List<Collection> allItems,
                                                                      List<String[]> usersCollectionSize, int i) {
        for(int x = 0; x < usersCollectionSize.size(); ++x) {
            if (Long.parseLong(usersCollectionSize.get(x)[ID]) == allItems.get(i).getUserCollection().getId()
                    && usersCollectionSize.get(x)[1].equals(allItems.get(i).getTopic())) {
                return NEXT;
            }
        }
        return CONTINUE;
    }

    private String[] setMaxSizeCollection(List<Collection> allItems, String[] maxSizeOfCollection, int p, int i) {
        maxSizeOfCollection[ID] = allItems.get(p).getUserCollection().getId().toString();
        maxSizeOfCollection[TOPIC] = allItems.get(p).getTopic();
        if (p == i) {
            maxSizeOfCollection[SIZE_OF_COLLECTION] = "0";
        }
        maxSizeOfCollection[SIZE_OF_COLLECTION] =
                String.valueOf(Integer.parseInt(maxSizeOfCollection[SIZE_OF_COLLECTION]) + 1);
        maxSizeOfCollection[LOGIN] = allItems.get(p).getUserCollection().getUsername();
        return maxSizeOfCollection;
    }

    public List<String[]> getGreatestCollections() {
        List<String[]> sizeOfEveryCollection = getSizeOfEveryCollection();
        List<String[]> greatestCollections = new ArrayList();
        for(int i = 0; i < sizeOfEveryCollection.size(); ++i) {
            this.compareCollectionsAndAddTheGreatestIntoList(greatestCollections, sizeOfEveryCollection);
            if (greatestCollections.size() == COUNT_OF_GREATEST_COLLECTIONS) {
                break;
            }
        }
        return greatestCollections;
    }

    private void compareCollectionsAndAddTheGreatestIntoList(List<String[]> greatestCollections,
                                                             List<String[]> sizeOfEveryCollection) {
        String[] greatestCollection = new String[COUNT_OF_ITEMS_IN_COLLECTION];
        for(int p = 0; p < sizeOfEveryCollection.size(); ++p) {
            int breakValue = checkAreTheSameItemsIntoTwoSameListsByIdAndTopic(greatestCollections,
                    sizeOfEveryCollection, p);
            if (breakValue != NEXT) {
                greatestCollection = setGreatestCollection(greatestCollection, sizeOfEveryCollection, p);
                this.addGreatestCollectionIntoTheList(greatestCollections, greatestCollection,
                        sizeOfEveryCollection, p);
            }
        }
    }

    private void addGreatestCollectionIntoTheList(List<String[]> greatestCollections, String[] greatestCollection,
                                                  List<String[]> sizeOfEveryCollection, int p) {
        if (p == sizeOfEveryCollection.size() - 1 && greatestCollections.size() < COUNT_OF_GREATEST_COLLECTIONS) {
            greatestCollections.add(greatestCollection);
        }
    }

    private String[] setGreatestCollection(String[] greatestCollection, List<String[]> sizeOfEveryCollection, int p) {
        greatestCollection = setGreatestCollectionIfItEmptyOrFirstIter(greatestCollection, sizeOfEveryCollection, p);
        if (Integer.parseInt(greatestCollection[SIZE_OF_COLLECTION]) <
                Integer.parseInt(sizeOfEveryCollection.get(p)[SIZE_OF_COLLECTION])) {
            greatestCollection = sizeOfEveryCollection.get(p);
        }
        return greatestCollection;
    }

    private String[] setGreatestCollectionIfItEmptyOrFirstIter(String[] greatestCollection,
                                                               List<String[]> sizeOfEveryCollection, int p) {
        return p != 0 && greatestCollection[SIZE_OF_COLLECTION] != null &&
                !greatestCollection[SIZE_OF_COLLECTION].isEmpty() ? greatestCollection : sizeOfEveryCollection.get(p);
    }

    private int checkAreTheSameItemsIntoTwoSameListsByIdAndTopic(List<String[]> greatestCollections,
                                                                 List<String[]> sizeOfEveryCollection, int p) {
        if (greatestCollections.size() > 0) {
            for(int z = 0; z < greatestCollections.size(); ++z) {
                if (greatestCollections.get(z)[ID].equals(sizeOfEveryCollection.get(p)[ID]) &&
                        greatestCollections.get(z)[TOPIC].equals(sizeOfEveryCollection.get(p)[TOPIC])) {
                    return NEXT;
                }
            }
        }
        return CONTINUE;
    }

    public List<Collection> getLastItems() {
        List<Collection> allItems = findAllItems();
        List<Collection> lastItems = new ArrayList();
        for(int i = allItems.size() - COUNT_OF_LAST_ITEMS; i < allItems.size(); ++i) {
            lastItems.add(allItems.get(i));
        }

        return lastItems;
    }

    public List<Collection> getItemsByWord(String word) {
        List<Collection> itemsByWord = new ArrayList();
        List<Long> allIdOfCollections = new ArrayList();
        allIdOfCollections.addAll(badgesTagsService.getIdForCollectionFromBadgesTagsByWord(word));
        allIdOfCollections.addAll(booksTagsService.getIdForCollectionFromBooksTagsByWord(word));
        allIdOfCollections.addAll(coinsTagsService.getIdForCollectionFromCoinsTagsByWord(word));
        allIdOfCollections.addAll(stampsTagsService.getIdForCollectionFromStampsTagsByWord(word));
        allIdOfCollections.addAll(wineTagsService.getIdForCollectionFromWineTagsByWord(word));
        for(int i = 0; i < allIdOfCollections.size(); ++i) {
            itemsByWord.add(findItemById(allIdOfCollections.get(i)));
        }
        return itemsByWord;
    }
}
