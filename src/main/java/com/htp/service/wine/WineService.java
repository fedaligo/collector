package com.htp.service.wine;

import com.htp.entity.wine.Wine;
import com.htp.repository.wine.WineRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class WineService {
    private final WineRepository wineRepository;

    public Wine findWineById(Long id){
        return wineRepository.findById(id).orElse(null);
    }

    public void deleteItem(Long id){
        wineRepository.deleteWineById(id);
    }

    public void saveItem(Wine wine){
        wineRepository.saveAndFlush(wine);
    }

    public void updateItem(Wine wine){
        wineRepository.save(wine);
    }
}
