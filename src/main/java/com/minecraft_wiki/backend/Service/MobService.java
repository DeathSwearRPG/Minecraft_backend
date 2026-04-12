package com.minecraft_wiki.backend.Service;

import com.minecraft_wiki.backend.Model.Mob;
import com.minecraft_wiki.backend.Model.enums.MobStrength;
import com.minecraft_wiki.backend.Model.enums.MobType;
import com.minecraft_wiki.backend.Repo.MobMongoRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MobService {

    private final MobMongoRepository mobMongoRepository;

    public List<Mob> getMobs(MobStrength strength, MobType type) {
        if (strength != null && type != null) {
            return mobMongoRepository.findByStrengthAndType(strength,type);
        }

        if (strength != null) {
            return mobMongoRepository.findByStrength(strength);
        }

        if (type != null) {
            return mobMongoRepository.findByType(type);
        }

        return mobMongoRepository.findAll();
    }

    public Mob getMobById(String mobId) {
        try {
            ObjectId objectId = new ObjectId(mobId);

            return mobMongoRepository.findById(objectId)
                    .orElseThrow(() -> new RuntimeException("mob doesn't exist " + mobId));

        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid mob id format: " + mobId);
        }
    }
}

