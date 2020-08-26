package com.htp.repository.collection;

import com.htp.entity.collection.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CollectionRepository extends CrudRepository<Collection, Long>, JpaRepository<Collection,Long> {
    Optional<Collection> findById(Long id);

    @Query("select hu from Collection hu ORDER BY hu.id")
    List<Collection> findAll();
}
