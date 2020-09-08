package com.htp.controller.collection;

import com.htp.entity.collection.Collection;
import com.htp.service.collection.CollectionService;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(
        origins = {"http://localhost:4200"}
)
public class CollectionController {
    private final CollectionService collectionService;

    @GetMapping({"/lastthreeitems"})
    public List<Collection> getLastThreeItems() {
        return this.collectionService.getLastItems();
    }

    @GetMapping({"/threegreatestcollections"})
    public List<String[]> getTheeGreatestCollections() {
        return this.collectionService.getGreatestCollections();
    }

    @GetMapping({"/itembyword/{word}"})
    public List<Collection> getItemsByWord(@PathVariable String word) {
        return this.collectionService.getItemsByWord(word);
    }

    @GetMapping({"/allitems"})
    public List<Collection> getAll() {
        return this.collectionService.findAllItems();
    }

    @GetMapping({"/finditem/{id}"})
    public Collection getAll(@PathVariable Long id) {
        return this.collectionService.findItemById(id);
    }

    public CollectionController(final CollectionService collectionService) {
        this.collectionService = collectionService;
    }
}
