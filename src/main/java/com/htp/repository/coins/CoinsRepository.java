package com.htp.repository.coins;

import com.htp.entity.coins.Coins;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CoinsRepository extends CrudRepository<Coins, Long>, JpaRepository<Coins,Long> {
    @Modifying
    @Query("delete from Coins hu where hu.id=:id")
    void deleteCoinsById(Long id);
}
