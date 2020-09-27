package com.htp.service.stamps;

import com.htp.entity.stamps.Stamps;
import com.htp.repository.stamps.StampsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class StampsService {
    private final StampsRepository stampsRepository;

    public Stamps findStampsById(Long id){
        return stampsRepository.findById(id).orElse(null);
    }

    public void deleteItem(Long id){
        stampsRepository.deleteStampsById(id);
    }

    public void saveItem(Stamps stamps){
        stampsRepository.saveAndFlush(stamps);
    }

    public void updateItem(Stamps stamps){
        stampsRepository.save(stamps);
    }
}
