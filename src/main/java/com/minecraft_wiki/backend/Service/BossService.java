package com.minecraft_wiki.backend.Service;

import com.minecraft_wiki.backend.Model.Boss;
import com.minecraft_wiki.backend.Model.enums.MobStrength;
import com.minecraft_wiki.backend.Model.enums.MobType;
import com.minecraft_wiki.backend.Repo.BossMongoRepository;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BossService {

    private final BossMongoRepository bossMongoRepository;

    public List<Boss> getBosses(MobType type) {
        if (type != null) {
            return bossMongoRepository.findByStrengthAndType(MobStrength.BOSS, type);
        }
        return bossMongoRepository.findByStrength(MobStrength.BOSS);
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
