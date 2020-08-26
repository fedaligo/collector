package com.htp.repository.tags;

import com.htp.entity.tags.WineTags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface WineTagsRepository extends CrudRepository<WineTags, Long>, JpaRepository<WineTags,Long> {
}
