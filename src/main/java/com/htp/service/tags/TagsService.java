package com.htp.service.tags;

import com.htp.entity.tags.Tags;
import com.htp.repository.tags.TagsRepository;
import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TagsService {
    private final TagsRepository tagsRepository;

    public List<Tags> getAllTags() {
        return tagsRepository.findAll();
    }
}
