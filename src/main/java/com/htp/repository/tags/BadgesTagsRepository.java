package com.htp.repository.tags;

import com.htp.entity.tags.BadgesTags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BadgesTagsRepository extends CrudRepository<BadgesTags, Long>, JpaRepository<BadgesTags,Long> {
    Optional<BadgesTags> findById(Long id);
}
