package com.htp.service.tags;

import com.htp.entity.tags.BooksTags;
import com.htp.entity.tags.Tags;
import com.htp.repository.tags.BooksTagsRepository;
import com.htp.service.AllService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<Tags> findTagsByItemId(Long id){
        List<Tags> tags = new ArrayList<>();
        List<BooksTags> books = findAllBooksTags();
        for(int i=0; i<books.size(); i++){
            if(books.get(i).getBooksBooksTags().getCollectionBooks().getId() == id){
                tags.add(books.get(i).getTagsBooksTags());
            }
        }
        return tags;
    }

    public BooksTags findBooksTagsByParameters(Long tagsId, Long booksId){
        return booksTagsRepository.findBooksTagsByParameters(tagsId, booksId);
    }

    public List<BooksTags> findBooksTagsByBooksId(Long booksId){
        return booksTagsRepository.findBooksTagsByBooksId(booksId);
    }

    public void deleteItem(Long id){
        booksTagsRepository.deleteBooksTagsById(id);
    }

    public void saveItem(BooksTags booksTags){
        booksTagsRepository.saveAndFlush(booksTags);
    }

    public void updateItem(BooksTags booksTags){
        booksTagsRepository.save(booksTags);
    }

}
