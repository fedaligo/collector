package com.htp.repository.coins;

import com.htp.entity.coins.Coins;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CoinsRepository extends CrudRepository<Coins, Long>, JpaRepository<Coins,Long> {
}
