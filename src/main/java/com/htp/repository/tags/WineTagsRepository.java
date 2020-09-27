package com.htp.repository.tags;

import com.htp.entity.tags.StampsTags;
import com.htp.entity.tags.WineTags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WineTagsRepository extends CrudRepository<WineTags, Long>, JpaRepository<WineTags,Long> {
    @Modifying
    @Query("delete from WineTags hu where hu.id=:id")
    void deleteWineTagsById(Long id);

    @Query("select hu from WineTags hu where hu.wineWineTags.id=:wineId AND " +
            "hu.tagsWineTags.id=:tagsId")
    WineTags findWineTagsByParameters(Long tagsId, Long wineId);

    @Query("select hu from WineTags hu where hu.wineWineTags.id=:wineId")
    List<WineTags> findWineTagsByWineId(Long wineId);
}
