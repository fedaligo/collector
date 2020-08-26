package com.htp.repository.tags;

import com.htp.entity.tags.CoinsTags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CoinsTagsRepository extends CrudRepository<CoinsTags, Long>, JpaRepository<CoinsTags,Long> {
}
