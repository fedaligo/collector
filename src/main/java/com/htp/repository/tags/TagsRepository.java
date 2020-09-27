package com.htp.repository.tags;

import com.htp.entity.tags.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TagsRepository extends CrudRepository<Tags, Long>, JpaRepository<Tags,Long> {

    @Query("select hu from Tags hu ORDER BY hu.id")
    List<Tags> findAll();

    Tags findTagsByName(String name);

    @Modifying
    @Query("delete from Tags hu where hu.id=:id")
    void deleteTagsById(Long id);
}
