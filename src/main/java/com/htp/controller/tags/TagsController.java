package com.htp.controller.tags;

import com.htp.entity.tags.BadgesTags;
import com.htp.entity.tags.Tags;
import com.htp.service.tags.BadgesTagsService;
import com.htp.service.tags.TagsService;
import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/tags")
@CrossOrigin(origins = {"http://localhost:4200"})
public class TagsController {
    private final TagsService tagsService;
    private final BadgesTagsService badgesTagsService;

    @GetMapping({"/alltags"})
    public List<Tags> getAllTags() {
        return tagsService.getAllTags();
    }

    @GetMapping({"/tagsnames"})
    public List<String> getBadgesTags(@RequestParam Long id) {
        return tagsService.getTagsNamesByItemId(id);
    }
}
