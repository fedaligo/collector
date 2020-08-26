package com.htp.repository.badges;

import com.htp.entity.badges.Badges;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface BadgesRepository extends CrudRepository<Badges, Long>, JpaRepository<Badges,Long> {
}
