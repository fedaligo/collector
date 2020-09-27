package com.htp.service.tags;

import com.htp.entity.tags.BadgesTags;
import com.htp.entity.tags.Tags;
import com.htp.entity.wine.Wine;
import com.htp.exceptions.EntityNotFoundException;
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

    public String[] getAllTagsNames(){
        List<Tags> allTags = getAllTags();
        String[] allTagsNames = new String[allTags.size()];
        for(int i=0; i<allTags.size(); i++){
            allTagsNames[i] = allTags.get(i).getName();
        }
        return allTagsNames;
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

    public List<Long> getTagsByItemId(Long id){
        List<Tags> allTagsById = new ArrayList<>();
        List<Long> allTagsId = new ArrayList<>();
        allTagsById.addAll(badgesTagsService.findTagsByItemId(id));
        allTagsById.addAll(booksTagsService.findTagsByItemId(id));
        allTagsById.addAll(coinsTagsService.findTagsByItemId(id));
        allTagsById.addAll(stampsTagsService.findTagsByItemId(id));
        allTagsById.addAll(wineTagsService.findTagsByItemId(id));
        for(int i =0 ; i< allTagsById.size(); i++){
            allTagsId.add(allTagsById.get(i).getId());
        }
        return allTagsId;
    }

    public Long saveTagAndGetId(String tagName){
        Tags tags = new Tags();
        tags.setName(tagName);
        tagsRepository.saveAndFlush(tags);
        return tagsRepository.findTagsByName(tagName).getId();
    }

    public Tags getTagsByName(String name){
        return tagsRepository.findTagsByName(name);
    }

    public Tags findTagsById(Long id){
        return tagsRepository.findById(id).orElse(null);
    }

    public void deleteItem(Long id){
        tagsRepository.deleteTagsById(id);
    }

    public void saveItem(Tags tags){
        tagsRepository.saveAndFlush(tags);
    }

    public void updateItem(Tags tags){
        tagsRepository.save(tags);
    }
}
