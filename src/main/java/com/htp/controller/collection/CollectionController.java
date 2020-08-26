package com.htp.controller.collection;

import com.htp.entity.collection.Collection;
import com.htp.repository.collection.CollectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CollectionController {
    private final CollectionRepository collectionRepository;

    @GetMapping("/task")
    public List<Collection> task() {
        return collectionRepository.findAll();
    }
}
