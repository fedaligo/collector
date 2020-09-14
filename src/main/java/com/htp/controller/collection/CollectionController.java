package com.htp.controller.collection;

import com.htp.entity.collection.Collection;
import com.htp.service.collection.CollectionService;
import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/collection")
@CrossOrigin(origins = {"http://localhost:4200"})
public class CollectionController {
    private final CollectionService collectionService;

    @GetMapping({"/lastthreeitems"})
    public List<Collection> getLastThreeItems() {
        return collectionService.getLastItems();
    }

    @GetMapping({"/threegreatestcollections"})
    public List<String[]> getTheeGreatestCollections() {
        return collectionService.getGreatestCollections();
    }

    @GetMapping({"/itembyword/{word}"})
    public List<Collection> getItemsByWord(@PathVariable String word) {
        return this.collectionService.getItemsByWord(word);
    }

    @GetMapping({"/allitems"})
    public List<Collection> getAll() {
        return collectionService.findAllItems();
    }

    @GetMapping({"/finditem/{id}"})
    public Collection getAll(@PathVariable Long id) {
        return collectionService.findItemById(id);
    }

}
