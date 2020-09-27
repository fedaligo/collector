package com.htp.repository.tags;

import com.htp.entity.tags.CoinsTags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CoinsTagsRepository extends CrudRepository<CoinsTags, Long>, JpaRepository<CoinsTags,Long> {

    @Modifying
    @Query("delete from CoinsTags hu where hu.id=:id")
    void deleteCoinsTagsById(Long id);

    @Query("select hu from CoinsTags hu where hu.coinsCoinsTags.id=:coinsId AND hu.tagsCoinsTags.id=:tagsId")
    CoinsTags findCoinsTagsByParameters(Long tagsId, Long coinsId);

    @Query("select hu from CoinsTags hu where hu.coinsCoinsTags.id=:coinsId")
    List<CoinsTags> findCoinsTagsByCoinsId(Long coinsId);
}
