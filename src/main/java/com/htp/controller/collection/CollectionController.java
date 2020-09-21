package com.htp.controller.collection;

import com.htp.entity.collection.Collection;
import com.htp.service.collection.CollectionService;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/collection")
@CrossOrigin(origins = {"http://localhost:4200"})
public class CollectionController {
    private static final int COUNT_OF_GREATEST_COLLECTIONS = 3;
    private final CollectionService collectionService;

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
}
