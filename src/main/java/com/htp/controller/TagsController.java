package com.htp.controller;

import com.htp.entity.tags.Tags;
import com.htp.service.tags.TagsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/tags")
@CrossOrigin(origins = {"*"})
public class TagsController {

    private final TagsService tagsService;

    @GetMapping({"/alltags"})
    public List<Tags> getAllTags() {
        return tagsService.getAllTags();
    }

    @GetMapping({"/alltagsnames"})
    public String[] getAllTagsNames() {
        return tagsService.getAllTagsNames();
    }

    @GetMapping({"/tagsnames"})
    public List<String> getTagsNamesByItemId(@RequestParam Long id) {
        return tagsService.getTagsNamesByItemId(id);
    }

    @GetMapping({"/tagsidbyitemid"})
    public List<Long> getTagsIdByItemId(@RequestParam Long id) {
        return tagsService.getTagsByItemId(id);
    }
}
