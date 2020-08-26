package com.htp.repository.wine;

import com.htp.entity.wine.Wine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface WineRepository extends CrudRepository<Wine, Long>, JpaRepository<Wine,Long> {
}
