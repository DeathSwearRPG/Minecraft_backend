package com.minecraft_wiki.backend.Service;

import com.minecraft_wiki.backend.Model.BaseMob;
import com.minecraft_wiki.backend.Model.Boss;
import com.minecraft_wiki.backend.Model.Mob;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class MobService {

    private final BossService bossService;
    private final PetService petService;

    private final List<Mob> mobs = List.of();


    public List<BaseMob> getAllMobs() {
        List<BaseMob> allMobs = new ArrayList<>();

        allMobs.addAll(mobs);
        allMobs.addAll(bossService.getBosses());
        allMobs.addAll(petService.getPets());

        return allMobs;
    }

    public BaseMob getMobById(UUID id) {
        return getAllMobs().stream()
                .filter(m -> m.getMobId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
