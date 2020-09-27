package com.htp.repository.books;

import com.htp.entity.books.Books;
import com.htp.entity.tags.BooksTags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface BooksRepository extends CrudRepository<Books, Long>, JpaRepository<Books,Long> {
    @Modifying
    @Query("delete from Books hu where hu.id=:id")
    void deleteBooksById(Long id);
}
