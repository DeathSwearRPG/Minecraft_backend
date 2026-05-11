package com.minecraft_wiki.backend.Service;

import com.minecraft_wiki.backend.Model.Boss;
import com.minecraft_wiki.backend.Model.enums.MobStrength;
import com.minecraft_wiki.backend.Model.enums.MobType;
import com.minecraft_wiki.backend.Repo.BossMongoRepository;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BossService {

    private final BossMongoRepository bossMongoRepository;

    public Page<Boss> getBosses(String search, MobType type, Pageable pageable) {
        return bossMongoRepository.findBosses(search, type, pageable);
    }

    public Boss getBossById(String bossId) {
        try {
            ObjectId objectId = new ObjectId(bossId);

            return bossMongoRepository.findByMobIdAndStrength(objectId, MobStrength.BOSS)
                    .orElseThrow(() -> new RuntimeException("boss doesn't exist " + bossId));

        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid boss id format: " + bossId);
        }
    }
}
