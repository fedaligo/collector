package com.htp.repository.likes;

import com.htp.entity.likes.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface LikesRepository extends CrudRepository<Likes, Long>, JpaRepository<Likes, Long> {

    @Modifying
    @Query("delete from Likes hu where hu.id=:id")
    void deleteLikesById(Long id);
}
