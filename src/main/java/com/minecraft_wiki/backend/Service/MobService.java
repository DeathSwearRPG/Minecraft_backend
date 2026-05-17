package com.minecraft_wiki.backend.Service;

import com.minecraft_wiki.backend.DTO.MobCreateDto;
import com.minecraft_wiki.backend.DTO.MobUpdateDto;
import com.minecraft_wiki.backend.Mapper.MobMapper;
import com.minecraft_wiki.backend.Model.Mob;
import com.minecraft_wiki.backend.Model.enums.MobStrength;
import com.minecraft_wiki.backend.Model.enums.MobType;
import com.minecraft_wiki.backend.Repo.MobMongoRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MobService {

    private final MobMongoRepository mobMongoRepository;
    private final MobMapper mobMapper;

    public Page<Mob> getMobs(String search, MobStrength strength, MobType type, Pageable pageable) {
        return mobMongoRepository.findMobs(search, strength, type, pageable);
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

    public Mob createMob(MobCreateDto dto) {

        Mob mob = mobMapper.fromCreateDto(dto);

        return mobMongoRepository.save(mob);
    }

    public Mob updateMob(String id, MobUpdateDto dto) {
        Mob mob = getMobById(id);

        mobMapper.updateMobFromDto(dto, mob);

        return mobMongoRepository.save(mob);
    }
}

