package com.htp.repository.books;

import com.htp.entity.tags.BooksTags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface BooksRepository extends CrudRepository<BooksTags, Long>, JpaRepository<BooksTags,Long> {
}
