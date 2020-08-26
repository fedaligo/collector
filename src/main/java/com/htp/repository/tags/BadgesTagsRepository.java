package com.htp.repository.tags;

import com.htp.entity.tags.BadgesTags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface BadgesTagsRepository extends CrudRepository<BadgesTags, Long>, JpaRepository<BadgesTags,Long> {
}
