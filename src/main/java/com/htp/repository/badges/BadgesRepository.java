package com.htp.repository.badges;

import com.htp.entity.badges.Badges;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface BadgesRepository extends CrudRepository<Badges, Long>, JpaRepository<Badges,Long> {
    @Modifying
    @Query("delete from Badges hu where hu.id=:id")
    void deleteBadgesById(Long id);
}
