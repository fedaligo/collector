package com.htp.service.coins;

import com.htp.entity.coins.Coins;
import com.htp.repository.coins.CoinsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CoinsService {

    private final CoinsRepository coinsRepository;

    public Coins findCoinsById(Long id){
        return coinsRepository.findById(id).orElse(null);
    }

    public void deleteItem(Long id){
        coinsRepository.deleteCoinsById(id);
    }

    public void saveItem(Coins coins){
        coinsRepository.saveAndFlush(coins);
    }

    public void updateItem(Coins coins){
        coinsRepository.save(coins);
    }
}
