package com.htp.service.collection;

import com.htp.controller.convert.badges.BadgesCreateRequestConverter;
import com.htp.controller.convert.badges.badgestags.BadgesTagsCreateRequestConverter;
import com.htp.controller.convert.collection.CollectionCreateRequestConverter;
import com.htp.controller.convert.tags.TagsCreateRequestConverter;
import com.htp.controller.requests.collection.CollectionCreateRequest;
import com.htp.entity.badges.Badges;
import com.htp.entity.books.Books;
import com.htp.entity.coins.Coins;
import com.htp.entity.collection.Collection;
import com.htp.entity.stamps.Stamps;
import com.htp.entity.tags.*;
import com.htp.entity.users.Users;
import com.htp.entity.wine.Wine;
import com.htp.repository.collection.CollectionRepository;
import com.htp.service.badges.BadgesService;
import com.htp.service.books.BooksService;
import com.htp.service.coins.CoinsService;
import com.htp.service.stamps.StampsService;
import com.htp.service.tags.*;

import java.util.ArrayList;
import java.util.List;

import com.htp.service.users.UsersService;
import com.htp.service.wine.WineService;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
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
    private static final int COUNT_OF_LAST_ITEMS = 3;

    private final CollectionRepository collectionRepository;
    private final BadgesService badgesService;
    private final BooksService booksService;
    private final CoinsService coinsService;
    private final StampsService stampsService;
    private final WineService wineService;
    private final TagsService tagsService;
    private final BadgesTagsService badgesTagsService;
    private final BooksTagsService booksTagsService;
    private final CoinsTagsService coinsTagsService;
    private final StampsTagsService stampsTagsService;
    private final WineTagsService wineTagsService;
    private final ConversionService conversionService;

    //private final UsersService usersService;


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

    public List<String[]> getGreatestCollections(int countOfGreatestCollections) {
        List<String[]> sizeOfEveryCollection = getSizeOfEveryCollection();
        List<String[]> greatestCollections = new ArrayList();
        for(int i = 0; i < sizeOfEveryCollection.size(); ++i) {
            this.compareCollectionsAndAddTheGreatestIntoList(greatestCollections, sizeOfEveryCollection);
            if (greatestCollections.size() == countOfGreatestCollections) {
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
        if (p == sizeOfEveryCollection.size() - 1) {
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
        allIdOfCollections.addAll(badgesTagsService.getIdForCollectionFromBadgesTagsByWord(word.toLowerCase()));
        allIdOfCollections.addAll(booksTagsService.getIdForCollectionFromBooksTagsByWord(word.toLowerCase()));
        allIdOfCollections.addAll(coinsTagsService.getIdForCollectionFromCoinsTagsByWord(word.toLowerCase()));
        allIdOfCollections.addAll(stampsTagsService.getIdForCollectionFromStampsTagsByWord(word.toLowerCase()));
        allIdOfCollections.addAll(wineTagsService.getIdForCollectionFromWineTagsByWord(word.toLowerCase()));
        for(int i = 0; i < allIdOfCollections.size(); ++i) {
            itemsByWord.add(findItemById(allIdOfCollections.get(i)));
        }
        return itemsByWord;
    }

    public List<Collection> findItemsByTopicAndUserName(String topic, String userName){
        return collectionRepository.findByTopicAndUserCollectionUsername(topic,userName);
    }

    public List<String[]> getCollectionsByUserName(String userName) {
        int countOfGreatestCollections = findAllItems().size();
        List<String[]> allCollections = getGreatestCollections(countOfGreatestCollections);
        List<String[]> collectionsByUserName = new ArrayList();
        for(int i = 0; i < allCollections.size(); ++i) {
            if (allCollections.get(i)[LOGIN].equals(userName)) {
                collectionsByUserName.add(allCollections.get(i));
            }
        }
        return collectionsByUserName;
    }

    public List<Collection> getItemsByUserName(String userName) {
        return collectionRepository.findCollectionByUserCollectionUsername(userName);
    }

    public void deleteItem(Long id){
        collectionRepository.deleteCollectionById(id);
    }

    public void saveBadgesItem(Collection collection, Badges badges, List<Tags> tags, List<BadgesTags> myBadgesTags){
        collectionRepository.saveAndFlush(collection);
        badges.setCollectionBadges(findItemById(collection.getId()));
        badgesService.saveItem(badges);
        for(int i=0; i<tags.size(); i++){
            tagsService.saveItem(tags.get(i));
            myBadgesTags.get(i).setBadgesBadgesTags(badgesService.findBadgesById(badges.getId()));
            myBadgesTags.get(i).setTagsBadgesTags(tagsService.findTagsById(tags.get(i).getId()));
            badgesTagsService.saveItem(myBadgesTags.get(i));
        }
    }
    public void saveBooksItem(Collection collection, Books books, List<Tags> tags, List<BooksTags> myBooksTags){
        collectionRepository.saveAndFlush(collection);
        books.setCollectionBooks(findItemById(collection.getId()));
        booksService.saveItem(books);
        for(int i=0; i<tags.size(); i++){
            tagsService.saveItem(tags.get(i));
            myBooksTags.get(i).setBooksBooksTags(booksService.findBooksById(books.getId()));
            myBooksTags.get(i).setTagsBooksTags(tagsService.findTagsById(tags.get(i).getId()));
            booksTagsService.saveItem(myBooksTags.get(i));
        }
    }
    public void saveCoinsItem(Collection collection, Coins coins, List<Tags> tags, List<CoinsTags> myCoinsTags){
        collectionRepository.saveAndFlush(collection);
        coins.setCollectionCoins(findItemById(collection.getId()));
        coinsService.saveItem(coins);
        for(int i=0; i<tags.size(); i++){
            tagsService.saveItem(tags.get(i));
            myCoinsTags.get(i).setCoinsCoinsTags(coinsService.findCoinsById(coins.getId()));
            myCoinsTags.get(i).setTagsCoinsTags(tagsService.findTagsById(tags.get(i).getId()));
            coinsTagsService.saveItem(myCoinsTags.get(i));
        }
    }
    public void saveStampsItem(Collection collection, Stamps stamps, List<Tags> tags, List<StampsTags> myStampsTags){
        collectionRepository.saveAndFlush(collection);
        stamps.setCollectionStamps(findItemById(collection.getId()));
        stampsService.saveItem(stamps);
        for(int i=0; i<tags.size(); i++){
            tagsService.saveItem(tags.get(i));
            myStampsTags.get(i).setStampsStampsTags(stampsService.findStampsById(stamps.getId()));
            myStampsTags.get(i).setTagsStampsTags(tagsService.findTagsById(tags.get(i).getId()));
            stampsTagsService.saveItem(myStampsTags.get(i));
        }
    }
    public void saveWineItem(Collection collection, Wine wine, List<Tags> tags, List<WineTags> myWineTags){
        collectionRepository.saveAndFlush(collection);
        wine.setCollectionWine(findItemById(collection.getId()));
        wineService.saveItem(wine);
        for(int i=0; i<tags.size(); i++){
            tagsService.saveItem(tags.get(i));
            myWineTags.get(i).setWineWineTags(wineService.findWineById(wine.getId()));
            myWineTags.get(i).setTagsWineTags(tagsService.findTagsById(tags.get(i).getId()));
            wineTagsService.saveItem(myWineTags.get(i));
        }
    }

    public void updateBadgesItem(Collection collection, Badges badges, List<Tags> tags, List<BadgesTags> myBadgesTags){
        collectionRepository.save(collection);
        badges.setCollectionBadges(findItemById(collection.getId()));
        badgesService.updateItem(badges);
        myBadgesTags = saveTagsAndSetParametersForBadgesTags(badges, tags, myBadgesTags);
        List<BadgesTags> oldBadgesTagsList = badgesTagsService.findBadgesTagsByBadgesId(badges.getId());
        for(int p=0; p<oldBadgesTagsList.size(); p++){
            iterateCollectionOfNewBadgesTagsAndDeleteOld(myBadgesTags, oldBadgesTagsList,p);
        }
        saveCollectionOfBadgesTags(myBadgesTags);

    }

    private List<BadgesTags> saveTagsAndSetParametersForBadgesTags(Badges badges, List<Tags> tags,
                                                                   List<BadgesTags> myBadgesTags){
        for(int i=0; i<tags.size(); i++){
            tagsService.saveItem(tags.get(i));
            myBadgesTags.get(i).setBadgesBadgesTags(badgesService.findBadgesById(badges.getId()));
            myBadgesTags.get(i).setTagsBadgesTags(tagsService.findTagsById(tags.get(i).getId()));
        }
        return myBadgesTags;
    }

    private void iterateCollectionOfNewBadgesTagsAndDeleteOld(List<BadgesTags> myBadgesTags,
                                                              List<BadgesTags> oldBadgesTagsList, int p){
        for(int z=0; z<myBadgesTags.size(); z++){
            if(oldBadgesTagsList.get(p).getId() == myBadgesTags.get(z).getId()){
                break;
            }
            if(oldBadgesTagsList.get(p).getId() != myBadgesTags.get(z).getId() &&
                    z == myBadgesTags.size() - 1){
                badgesTagsService.deleteItem(oldBadgesTagsList.get(p).getId());
            }
        }
    }

    private void saveCollectionOfBadgesTags(List<BadgesTags> myBadgesTags){
        for(int k=0; k<myBadgesTags.size(); k++){
            badgesTagsService.saveItem(myBadgesTags.get(k));
        }
    }

    public void updateBooksItem(Collection collection, Books books, List<Tags> tags, List<BooksTags> myBooksTags){
        collectionRepository.save(collection);
        books.setCollectionBooks(findItemById(collection.getId()));
        booksService.updateItem(books);
        myBooksTags = saveTagsAndSetParametersForBooksTags(books, tags, myBooksTags);
        List<BooksTags> oldBooksTagsList = booksTagsService.findBooksTagsByBooksId(books.getId());
        for(int p=0; p<oldBooksTagsList.size(); p++){
            iterateCollectionOfNewBooksTagsAndDeleteOld(myBooksTags, oldBooksTagsList,p);
        }
        saveCollectionOfBooksTags(myBooksTags);

    }

    private List<BooksTags> saveTagsAndSetParametersForBooksTags(Books books, List<Tags> tags,
                                                                 List<BooksTags> myBooksTags){
        for(int i=0; i<tags.size(); i++){
            tagsService.saveItem(tags.get(i));
            myBooksTags.get(i).setBooksBooksTags(booksService.findBooksById(books.getId()));
            myBooksTags.get(i).setTagsBooksTags(tagsService.findTagsById(tags.get(i).getId()));
        }
        return myBooksTags;
    }

    private void iterateCollectionOfNewBooksTagsAndDeleteOld(List<BooksTags> myBooksTags,
                                                             List<BooksTags> oldBooksTagsList, int p){
        for(int z=0; z<myBooksTags.size(); z++){
            if(oldBooksTagsList.get(p).getId() == myBooksTags.get(z).getId()){
                break;
            }
            if(oldBooksTagsList.get(p).getId() != myBooksTags.get(z).getId() &&
                    z == myBooksTags.size() - 1){
                booksTagsService.deleteItem(oldBooksTagsList.get(p).getId());
            }
        }
    }

    private void saveCollectionOfBooksTags(List<BooksTags> myBooksTags){
        for(int k=0; k<myBooksTags.size(); k++){
            booksTagsService.saveItem(myBooksTags.get(k));
        }
    }


    public void updateCoinsItem(Collection collection, Coins coins, List<Tags> tags, List<CoinsTags> myCoinsTags){
        collectionRepository.save(collection);
        coins.setCollectionCoins(findItemById(collection.getId()));
        coinsService.updateItem(coins);
        myCoinsTags = saveTagsAndSetParametersForCoinsTags(coins, tags, myCoinsTags);
        List<CoinsTags> oldCoinsTagsList = coinsTagsService.findCoinsTagsByCoinsId(coins.getId());
        for(int p=0; p<oldCoinsTagsList.size(); p++){
            iterateCollectionOfNewCoinsTagsAndDeleteOld(myCoinsTags, oldCoinsTagsList,p);
        }
        saveCollectionOfCoinsTags(myCoinsTags);

    }

    private List<CoinsTags> saveTagsAndSetParametersForCoinsTags(Coins coins, List<Tags> tags,
                                                                 List<CoinsTags> myCoinsTags){
        for(int i=0; i<tags.size(); i++){
            tagsService.saveItem(tags.get(i));
            myCoinsTags.get(i).setCoinsCoinsTags(coinsService.findCoinsById(coins.getId()));
            myCoinsTags.get(i).setTagsCoinsTags(tagsService.findTagsById(tags.get(i).getId()));
        }
        return myCoinsTags;
    }

    private void iterateCollectionOfNewCoinsTagsAndDeleteOld(List<CoinsTags> myCoinsTags,
                                                             List<CoinsTags> oldCoinsTagsList, int p){
        for(int z=0; z<myCoinsTags.size(); z++){
            if(oldCoinsTagsList.get(p).getId() == myCoinsTags.get(z).getId()){
                break;
            }
            if(oldCoinsTagsList.get(p).getId() != myCoinsTags.get(z).getId() &&
                    z == myCoinsTags.size() - 1){
                coinsTagsService.deleteItem(oldCoinsTagsList.get(p).getId());
            }
        }
    }

    private void saveCollectionOfCoinsTags(List<CoinsTags> myCoinsTags){
        for(int k=0; k<myCoinsTags.size(); k++){
            coinsTagsService.saveItem(myCoinsTags.get(k));
        }
    }


    public void updateStampsItem(Collection collection, Stamps stamps, List<Tags> tags, List<StampsTags> myStampsTags){
        collectionRepository.save(collection);
        stamps.setCollectionStamps(findItemById(collection.getId()));
        stampsService.updateItem(stamps);
        myStampsTags = saveTagsAndSetParametersForStampsTags(stamps, tags, myStampsTags);
        List<StampsTags> oldStampsTagsList = stampsTagsService.findStampsTagsByStampsId(stamps.getId());
        for(int p=0; p<oldStampsTagsList.size(); p++){
            iterateCollectionOfNewStampsTagsAndDeleteOld(myStampsTags, oldStampsTagsList,p);
        }
        saveCollectionOfStampsTags(myStampsTags);

    }

    private List<StampsTags> saveTagsAndSetParametersForStampsTags(Stamps stamps, List<Tags> tags,
                                                                   List<StampsTags> myStampsTags){
        for(int i=0; i<tags.size(); i++){
            tagsService.saveItem(tags.get(i));
            myStampsTags.get(i).setStampsStampsTags(stampsService.findStampsById(stamps.getId()));
            myStampsTags.get(i).setTagsStampsTags(tagsService.findTagsById(tags.get(i).getId()));
        }
        return myStampsTags;
    }

    private void iterateCollectionOfNewStampsTagsAndDeleteOld(List<StampsTags> myStampsTags,
                                                              List<StampsTags> oldStampsTagsList, int p){
        for(int z=0; z<myStampsTags.size(); z++){
            if(oldStampsTagsList.get(p).getId() == myStampsTags.get(z).getId()){
                break;
            }
            if(oldStampsTagsList.get(p).getId() != myStampsTags.get(z).getId() &&
                    z == myStampsTags.size() - 1){
                stampsTagsService.deleteItem(oldStampsTagsList.get(p).getId());
            }
        }
    }

    private void saveCollectionOfStampsTags(List<StampsTags> myStampsTags){
        for(int k=0; k<myStampsTags.size(); k++){
            stampsTagsService.saveItem(myStampsTags.get(k));
        }
    }


    public void updateWineItem(Collection collection, Wine wine, List<Tags> tags, List<WineTags> myWineTags){
        collectionRepository.save(collection);
        wine.setCollectionWine(findItemById(collection.getId()));
        wineService.updateItem(wine);
        myWineTags = saveTagsAndSetParametersForWineTags(wine, tags, myWineTags);
        List<WineTags> oldWineTagsList = wineTagsService.findWineTagsByWineId(wine.getId());
        for(int p=0; p<oldWineTagsList.size(); p++){
            iterateCollectionOfNewWineTagsAndDeleteOld(myWineTags, oldWineTagsList,p);
        }
        saveCollectionOfWineTags(myWineTags);

    }

    private List<WineTags> saveTagsAndSetParametersForWineTags(Wine wine, List<Tags> tags,
                                                               List<WineTags> myWineTags){
        for(int i=0; i<tags.size(); i++){
            tagsService.saveItem(tags.get(i));
            myWineTags.get(i).setWineWineTags(wineService.findWineById(wine.getId()));
            myWineTags.get(i).setTagsWineTags(tagsService.findTagsById(tags.get(i).getId()));
        }
        return myWineTags;
    }

    private void iterateCollectionOfNewWineTagsAndDeleteOld(List<WineTags> myWineTags,
                                                            List<WineTags> oldWineTagsList, int p){
        for(int z=0; z<myWineTags.size(); z++){
            if(oldWineTagsList.get(p).getId() == myWineTags.get(z).getId()){
                break;
            }
            if(oldWineTagsList.get(p).getId() != myWineTags.get(z).getId() &&
                    z == myWineTags.size() - 1){
                wineTagsService.deleteItem(oldWineTagsList.get(p).getId());
            }
        }
    }

    private void saveCollectionOfWineTags(List<WineTags> myWineTags){
        for(int k=0; k<myWineTags.size(); k++){
            wineTagsService.saveItem(myWineTags.get(k));
        }
    }

}
