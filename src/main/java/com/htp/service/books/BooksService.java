package com.htp.service.books;

import com.htp.entity.books.Books;
import com.htp.repository.books.BooksRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BooksService {

    private final BooksRepository booksRepository;

    public Books findBooksById(Long id){
        return booksRepository.findById(id).orElse(null);
    }

    public void deleteItem(Long id){
        booksRepository.deleteBooksById(id);
    }

    public void saveItem(Books books){
        booksRepository.saveAndFlush(books);
    }

    public void updateItem(Books books){
        booksRepository.save(books);
    }
}
