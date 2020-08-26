package com.htp.repository.tags;

import com.htp.entity.tags.StampsTags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface StampsTagsRepository extends CrudRepository<StampsTags, Long>, JpaRepository<StampsTags,Long> {
}
