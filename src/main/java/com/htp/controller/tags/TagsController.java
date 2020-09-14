package com.htp.controller.tags;

import com.htp.entity.tags.Tags;
import com.htp.service.tags.TagsService;
import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/tags")
@CrossOrigin(origins = {"http://localhost:4200"})
public class TagsController {
    private final TagsService tagsService;

    @GetMapping({"/alltags"})
    public List<Tags> getAllTags() {
        return tagsService.getAllTags();
    }
}
