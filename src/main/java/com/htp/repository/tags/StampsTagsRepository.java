package com.htp.repository.tags;

import com.htp.entity.tags.StampsTags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StampsTagsRepository extends CrudRepository<StampsTags, Long>, JpaRepository<StampsTags,Long> {

    @Modifying
    @Query("delete from StampsTags hu where hu.id=:id")
    void deleteStampsTagsById(Long id);

    @Query("select hu from StampsTags hu where hu.stampsStampsTags.id=:stampsId AND hu.tagsStampsTags.id=:tagsId")
    StampsTags findStampsTagsByParameters(Long tagsId, Long stampsId);

    @Query("select hu from StampsTags hu where hu.stampsStampsTags.id=:stampsId")
    List<StampsTags> findStampsTagsByStampsId(Long stampsId);
}
