package com.htp.repository.tags;

import com.htp.entity.tags.BooksTags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface BooksTagsRepository extends CrudRepository<BooksTags, Long>, JpaRepository<BooksTags,Long> {
}
