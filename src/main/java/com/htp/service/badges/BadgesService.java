package com.htp.service.badges;

import com.htp.entity.badges.Badges;
import com.htp.repository.badges.BadgesRepository;
import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BadgesService {
    private final BadgesRepository badgesRepository;

    public List<Badges> findAllBadges() {
        return badgesRepository.findAll();
    }

}
