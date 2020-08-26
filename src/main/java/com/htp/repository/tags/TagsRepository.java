package com.htp.repository.tags;

import com.htp.entity.tags.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface TagsRepository extends CrudRepository<Tags, Long>, JpaRepository<Tags,Long> {
}
