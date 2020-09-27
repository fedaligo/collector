package com.htp.repository.comments;

import com.htp.entity.comments.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CommentsRepository extends CrudRepository<Comments, Long>, JpaRepository<Comments, Long> {

    @Modifying
    @Query("delete from Comments hu where hu.id=:id")
    void deleteCommentsById(Long id);
}
