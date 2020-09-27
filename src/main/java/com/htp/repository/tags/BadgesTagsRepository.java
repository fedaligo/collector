package com.htp.repository.tags;

import com.htp.entity.tags.BadgesTags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BadgesTagsRepository extends CrudRepository<BadgesTags, Long>, JpaRepository<BadgesTags,Long> {

    Optional<BadgesTags> findById(Long id);

    @Modifying
    @Query("delete from BadgesTags hu where hu.id=:id")
    void deleteBadgesTagsById(Long id);

    @Query("select hu from BadgesTags hu where hu.badgesBadgesTags.id=:badgesId AND hu.tagsBadgesTags.id=:tagsId")
    BadgesTags findBadgesTagsByParameters(Long tagsId, Long badgesId);

    @Query("select hu from BadgesTags hu where hu.badgesBadgesTags.id=:badgesId")
    List<BadgesTags> findBadgesTagsByBadgesId(Long badgesId);
}
