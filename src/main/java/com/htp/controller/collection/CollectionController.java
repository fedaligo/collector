package com.htp.controller.collection;

import com.htp.controller.convert.badges.BadgesChangeRequestConverter;
import com.htp.controller.convert.badges.BadgesCreateRequestConverter;
import com.htp.controller.convert.badges.badgestags.BadgesTagsChangeRequestConverter;
import com.htp.controller.convert.badges.badgestags.BadgesTagsCreateRequestConverter;
import com.htp.controller.convert.books.BooksChangeRequestConverter;
import com.htp.controller.convert.books.BooksCreateRequestConverter;
import com.htp.controller.convert.books.bookstags.BooksTagsChangeRequestConverter;
import com.htp.controller.convert.books.bookstags.BooksTagsCreateRequestConverter;
import com.htp.controller.convert.coins.CoinsChangeRequestConverter;
import com.htp.controller.convert.coins.CoinsCreateRequestConverter;
import com.htp.controller.convert.coins.coinstags.CoinsTagsChangeRequestConverter;
import com.htp.controller.convert.coins.coinstags.CoinsTagsCreateRequestConverter;
import com.htp.controller.convert.collection.CollectionChangeRequestConverter;
import com.htp.controller.convert.collection.CollectionCreateRequestConverter;
import com.htp.controller.convert.collection.CollectionRequestConverter;
import com.htp.controller.convert.stamps.StampsChangeRequestConverter;
import com.htp.controller.convert.stamps.StampsCreateRequestConverter;
import com.htp.controller.convert.stamps.stampstags.StampsTagsChangeRequestConverter;
import com.htp.controller.convert.stamps.stampstags.StampsTagsCreateRequestConverter;
import com.htp.controller.convert.tags.TagsChangeRequestConverter;
import com.htp.controller.convert.tags.TagsCreateRequestConverter;
import com.htp.controller.convert.wine.WineChangeRequestConverter;
import com.htp.controller.convert.wine.WineCreateRequestConverter;
import com.htp.controller.convert.wine.winetags.WineTagsChangeRequestConverter;
import com.htp.controller.convert.wine.winetags.WineTagsCreateRequestConverter;
import com.htp.controller.requests.collection.CollectionCreateRequest;
import com.htp.controller.requests.collection.CollectionUpdateRequest;
import com.htp.controller.requests.users.UserCreateRequest;
import com.htp.controller.requests.users.UserUpdateRequest;
import com.htp.entity.badges.Badges;
import com.htp.entity.books.Books;
import com.htp.entity.coins.Coins;
import com.htp.entity.collection.Collection;
import com.htp.entity.stamps.Stamps;
import com.htp.entity.tags.*;
import com.htp.entity.users.Users;
import com.htp.entity.wine.Wine;
import com.htp.service.collection.CollectionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/collection")
@CrossOrigin(origins = {"*"})
public class CollectionController {
    private static final int COUNT_OF_GREATEST_COLLECTIONS = 3;
    private final CollectionService collectionService;
    private final ConversionService conversionService;

    private final CollectionCreateRequestConverter converter;
    private final BadgesCreateRequestConverter badgesConverter;
    private final BooksCreateRequestConverter booksConverter;
    private final CoinsCreateRequestConverter coinsConverter;
    private final StampsCreateRequestConverter stampsConverter;
    private final WineCreateRequestConverter wineConverter;
    private final TagsCreateRequestConverter tagsConverter;
    private final BadgesTagsCreateRequestConverter badgesTagsConverter;
    private final BooksTagsCreateRequestConverter booksTagsConverter;
    private final CoinsTagsCreateRequestConverter coinsTagsConverter;
    private final StampsTagsCreateRequestConverter stampsTagsConverter;
    private final WineTagsCreateRequestConverter wineTagsConverter;

    private final CollectionChangeRequestConverter converterUpdate;
    private final BadgesChangeRequestConverter badgesConverterUpdate;
    private final BooksChangeRequestConverter booksConverterUpdate;
    private final CoinsChangeRequestConverter coinsConverterUpdate;
    private final StampsChangeRequestConverter stampsConverterUpdate;
    private final WineChangeRequestConverter wineConverterUpdate;
    private final TagsChangeRequestConverter tagsConverterUpdate;
    private final BadgesTagsChangeRequestConverter badgesTagsConverterUpdate;
    private final BooksTagsChangeRequestConverter booksTagsConverterUpdate;
    private final CoinsTagsChangeRequestConverter coinsTagsConverterUpdate;
    private final StampsTagsChangeRequestConverter stampsTagsConverterUpdate;
    private final WineTagsChangeRequestConverter wineTagsConverterUpdate;

    @GetMapping({"/lastthreeitems"})
    public List<Collection> getLastThreeItems() {
        return collectionService.getLastItems();
    }

    @GetMapping({"/threegreatestcollections"})
    public List<String[]> getTheeGreatestCollections() {
        return collectionService.getGreatestCollections(COUNT_OF_GREATEST_COLLECTIONS);
    }

    @GetMapping({"/itembyword/{word}"})
    public List<Collection> getItemsByWord(@PathVariable String word) {
        return this.collectionService.getItemsByWord(word);
    }

    @GetMapping({"/allitems"})
    public List<Collection> getAllItems() {
        return collectionService.findAllItems();
    }

    @GetMapping({"/allcollections"})
    public List<String[]> getAllCollections() {
        int countOfGreatestCollections = getAllItems().size();
        return collectionService.getGreatestCollections(countOfGreatestCollections);
    }

    @GetMapping({"/allcollectionsbyuser"})
    public List<String[]> getAllCollectionsByUser(@RequestParam String username) {
        return collectionService.getCollectionsByUserName(username);
    }

    @GetMapping({"/allitemsbyuser"})
    public List<Collection> getAllItemsByUser(@RequestParam String username) {
        return collectionService.getItemsByUserName(username);
    }

    @GetMapping({"/finditem/{id}"})
    public Collection getAll(@PathVariable Long id) {
        return collectionService.findItemById(id);
    }

    @GetMapping({"/itemsofcollections"})
    public List<Collection> getAll(@RequestParam String topic, @RequestParam String username) {
        return collectionService.findItemsByTopicAndUserName(topic, username);
    }

    /*@PutMapping("/updateitem")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> updateItem(@RequestBody @Valid CollectionUpdateRequest request) {
        collectionService.updateItem(conversionService.convert(request, Collection.class));
        return ResponseEntity.ok("Item was updated");
    }*/

    @PutMapping("/updatebadges")
    @Transactional(rollbackFor = {Exception.class})
    public ResponseEntity<?> updateBadges(@RequestBody @Valid CollectionUpdateRequest request) {
        Collection collection = converterUpdate.convert(request);
        List<Tags> myTags = new ArrayList<>();
        List<BadgesTags> myBadgesTags = new ArrayList<>();
        for (int i = 0; i < request.getTagsName().length; i++) {
            request.setTagsIterate(request.getTagsName()[i]);
            myTags.add(tagsConverterUpdate.convert(request.getTagsName()[i]));
            myBadgesTags.add(badgesTagsConverterUpdate.convert(request));
        }
        Badges badges = badgesConverterUpdate.convert(request);
        collectionService.updateBadgesItem(collection,badges,myTags,myBadgesTags);
        return ResponseEntity.ok("Item was updated");
    }

    @PutMapping("/updatebooks")
    @Transactional(rollbackFor = {Exception.class})
    public ResponseEntity<?> updateBooks(@RequestBody @Valid CollectionUpdateRequest request) {
        Collection collection = converterUpdate.convert(request);
        List<Tags> myTags = new ArrayList<>();
        List<BooksTags> myBooksTags = new ArrayList<>();
        for (int i = 0; i < request.getTagsName().length; i++) {
            request.setTagsIterate(request.getTagsName()[i]);
            myTags.add(tagsConverterUpdate.convert(request.getTagsName()[i]));
            myBooksTags.add(booksTagsConverterUpdate.convert(request));
        }
        Books books = booksConverterUpdate.convert(request);
        collectionService.updateBooksItem(collection,books,myTags,myBooksTags);
        return ResponseEntity.ok("Item was updated");
    }

    @PutMapping("/updatecoins")
    @Transactional(rollbackFor = {Exception.class})
    public ResponseEntity<?> updateCoins(@RequestBody @Valid CollectionUpdateRequest request) {
        Collection collection = converterUpdate.convert(request);
        List<Tags> myTags = new ArrayList<>();
        List<CoinsTags> myCoinsTags = new ArrayList<>();
        for (int i = 0; i < request.getTagsName().length; i++) {
            request.setTagsIterate(request.getTagsName()[i]);
            myTags.add(tagsConverterUpdate.convert(request.getTagsName()[i]));
            myCoinsTags.add(coinsTagsConverterUpdate.convert(request));
        }
        Coins coins = coinsConverterUpdate.convert(request);
        collectionService.updateCoinsItem(collection,coins,myTags,myCoinsTags);
        return ResponseEntity.ok("Item was updated");
    }

    @PutMapping("/updatestamps")
    @Transactional(rollbackFor = {Exception.class})
    public ResponseEntity<?> updateStamps(@RequestBody @Valid CollectionUpdateRequest request) {
        Collection collection = converterUpdate.convert(request);
        List<Tags> myTags = new ArrayList<>();
        List<StampsTags> myStampsTags = new ArrayList<>();
        for (int i = 0; i < request.getTagsName().length; i++) {
            request.setTagsIterate(request.getTagsName()[i]);
            myTags.add(tagsConverterUpdate.convert(request.getTagsName()[i]));
            myStampsTags.add(stampsTagsConverterUpdate.convert(request));
        }
        Stamps stamps = stampsConverterUpdate.convert(request);
        collectionService.updateStampsItem(collection,stamps,myTags,myStampsTags);
        return ResponseEntity.ok("Item was updated");
    }

    @PutMapping("/updatewine")
    @Transactional(rollbackFor = {Exception.class})
    public ResponseEntity<?> updateWine(@RequestBody @Valid CollectionUpdateRequest request) {
        Collection collection = converterUpdate.convert(request);
        List<Tags> myTags = new ArrayList<>();
        List<WineTags> myWineTags = new ArrayList<>();
        for (int i = 0; i < request.getTagsName().length; i++) {
            request.setTagsIterate(request.getTagsName()[i]);
            myTags.add(tagsConverterUpdate.convert(request.getTagsName()[i]));
            myWineTags.add(wineTagsConverterUpdate.convert(request));
        }
        Wine wine = wineConverterUpdate.convert(request);
        collectionService.updateWineItem(collection,wine,myTags,myWineTags);
        return ResponseEntity.ok("Item was updated");
    }

    @PostMapping("/createbadges")
    @Transactional(rollbackFor = {Exception.class})
    public ResponseEntity<?> createBadges(@RequestBody @Valid CollectionCreateRequest request) {
        Collection collection = converter.convert(request);
        List<Tags> myTags = new ArrayList<>();
        List<BadgesTags> myBadgesTags = new ArrayList<>();
        for (int i = 0; i < request.getTagsName().length; i++) {
            myTags.add(tagsConverter.convert(request.getTagsName()[i]));
            myBadgesTags.add(badgesTagsConverter.convert(request));
        }
            Badges badges = badgesConverter.convert(request);
            collectionService.saveBadgesItem(collection,badges,myTags,myBadgesTags);
        return ResponseEntity.ok("Item was created");
    }

    @PostMapping("/createbooks")
    @Transactional(rollbackFor = {Exception.class})
    public ResponseEntity<?> createBooks(@RequestBody @Valid CollectionCreateRequest request) {
        Collection collection = converter.convert(request);
        List<Tags> myTags = new ArrayList<>();
        List<BooksTags> myBooksTags = new ArrayList<>();
        for (int i = 0; i < request.getTagsName().length; i++) {
            myTags.add(tagsConverter.convert(request.getTagsName()[i]));
            myBooksTags.add(booksTagsConverter.convert(request));
        }
        Books books = booksConverter.convert(request);
        collectionService.saveBooksItem(collection,books,myTags,myBooksTags);
        return ResponseEntity.ok("Item was created");
    }

    @PostMapping("/createcoins")
    @Transactional(rollbackFor = {Exception.class})
    public ResponseEntity<?> createCoins(@RequestBody @Valid CollectionCreateRequest request) {
        Collection collection = converter.convert(request);
        List<Tags> myTags = new ArrayList<>();
        List<CoinsTags> myCoinsTags = new ArrayList<>();
        for (int i = 0; i < request.getTagsName().length; i++) {
            myTags.add(tagsConverter.convert(request.getTagsName()[i]));
            myCoinsTags.add(coinsTagsConverter.convert(request));
        }
        Coins coins = coinsConverter.convert(request);
        collectionService.saveCoinsItem(collection,coins,myTags,myCoinsTags);
        return ResponseEntity.ok("Item was created");
    }

    @PostMapping("/createstamps")
    @Transactional(rollbackFor = {Exception.class})
    public ResponseEntity<?> createStamps(@RequestBody @Valid CollectionCreateRequest request) {
        Collection collection = converter.convert(request);
        List<Tags> myTags = new ArrayList<>();
        List<StampsTags> myStampsTags = new ArrayList<>();
        for (int i = 0; i < request.getTagsName().length; i++) {
            myTags.add(tagsConverter.convert(request.getTagsName()[i]));
            myStampsTags.add(stampsTagsConverter.convert(request));
        }
        Stamps stamps = stampsConverter.convert(request);
        collectionService.saveStampsItem(collection,stamps,myTags,myStampsTags);
        return ResponseEntity.ok("Item was created");
    }

    @PostMapping("/createwine")
    @Transactional(rollbackFor = {Exception.class})
    public ResponseEntity<?> createWine(@RequestBody @Valid CollectionCreateRequest request) {
        Collection collection = converter.convert(request);
        List<Tags> myTags = new ArrayList<>();
        List<WineTags> myWineTags = new ArrayList<>();
        for (int i = 0; i < request.getTagsName().length; i++) {
            myTags.add(tagsConverter.convert(request.getTagsName()[i]));
            myWineTags.add(wineTagsConverter.convert(request));
        }
        Wine wine = wineConverter.convert(request);
        collectionService.saveWineItem(collection,wine,myTags,myWineTags);
        return ResponseEntity.ok("Item was created");
    }

    @DeleteMapping("/deleteitem/{id}")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> deleteItem(@PathVariable Long id) {
        collectionService.deleteItem(id);
        return ResponseEntity.ok("Item was deleted");
    }
}
