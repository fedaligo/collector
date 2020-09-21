package com.htp.service.tags;

import com.htp.entity.tags.BadgesTags;
import com.htp.entity.tags.BooksTags;
import com.htp.repository.tags.BooksTagsRepository;
import com.htp.service.AllService;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BooksTagsService {
    private static final int CONTINUE = 0;
    private static final int NEXT = 1;
    private final AllService allService;
    private final BooksTagsRepository booksTagsRepository;

    public List<BooksTags> findAllBooksTags() {
        return booksTagsRepository.findAll();
    }

    public List<Long> getIdForCollectionFromBooksTagsByWord(String word) {
        List<BooksTags> books = findAllBooksTags();
        List<Long> saveIdForMappingByCollection = new ArrayList();
        for(int i = 0; i < books.size(); ++i) {
            long idValue = books.get(i).getBooksBooksTags().getCollectionBooks().getId();
            if (findWordInBooksAndGetBreakValue(books, word, i, saveIdForMappingByCollection, idValue) != NEXT) {
                saveIdForMappingByCollection.add(idValue);
            }
        }
        return saveIdForMappingByCollection;
    }

    private int findWordInBooksAndGetBreakValue(List<BooksTags> books, String word, int i, List<Long> saveIdForMappingByCollection, long idValue) {
        int breakValue = CONTINUE;
        String info = books.get(i).toString();
        info = allService.getOnlyInfoFromString(info);
        if (info.contains(word)) {
            if (saveIdForMappingByCollection.size() > 0) {
                breakValue = allService.checkIdIsNotRepeated(saveIdForMappingByCollection, idValue);
            }
        } else {
            breakValue = NEXT;
        }

        return breakValue;
    }

    public List<String> findNameOfTagsByItemId(Long id){
        List<String> namesOfTags = new ArrayList<>();
        List<BooksTags> books = findAllBooksTags();
        for(int i=0; i<books.size(); i++){
            if(books.get(i).getBooksBooksTags().getCollectionBooks().getId() == id){
                namesOfTags.add(books.get(i).getTagsBooksTags().getName());
            }
        }
        return namesOfTags;
    }
}
