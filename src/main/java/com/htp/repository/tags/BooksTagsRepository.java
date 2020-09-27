package com.htp.repository.tags;

import com.htp.entity.tags.BadgesTags;
import com.htp.entity.tags.BooksTags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BooksTagsRepository extends CrudRepository<BooksTags, Long>, JpaRepository<BooksTags,Long> {
    @Modifying
    @Query("delete from BooksTags hu where hu.id=:id")
    void deleteBooksTagsById(Long id);

    @Query("select hu from BooksTags hu where hu.booksBooksTags.id=:booksId AND " +
            "hu.tagsBooksTags.id=:tagsId")
    BooksTags findBooksTagsByParameters(Long tagsId, Long booksId);

    @Query("select hu from BooksTags hu where hu.booksBooksTags.id=:booksId")
    List<BooksTags> findBooksTagsByBooksId(Long booksId);
}
