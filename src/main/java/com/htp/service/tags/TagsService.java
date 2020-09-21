package com.htp.service.tags;

import com.htp.entity.tags.BadgesTags;
import com.htp.entity.tags.Tags;
import com.htp.repository.tags.TagsRepository;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TagsService {
    private final TagsRepository tagsRepository;
    private final BadgesTagsService badgesTagsService;
    private final BooksTagsService booksTagsService;
    private final CoinsTagsService coinsTagsService;
    private final StampsTagsService stampsTagsService;
    private final WineTagsService wineTagsService;

    public List<Tags> getAllTags() {
        return tagsRepository.findAll();
    }

    public List<String> getTagsNamesByItemId(Long id){
        List<String> allTagsNamesById = new ArrayList<>();
        allTagsNamesById.addAll(badgesTagsService.findNameOfTagsByItemId(id));
        allTagsNamesById.addAll(booksTagsService.findNameOfTagsByItemId(id));
        allTagsNamesById.addAll(coinsTagsService.findNameOfTagsByItemId(id));
        allTagsNamesById.addAll(stampsTagsService.findNameOfTagsByItemId(id));
        allTagsNamesById.addAll(wineTagsService.findNameOfTagsByItemId(id));
        return allTagsNamesById;
    }
}
