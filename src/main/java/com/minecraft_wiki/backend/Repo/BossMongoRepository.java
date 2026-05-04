package com.minecraft_wiki.backend.Repo;

import com.minecraft_wiki.backend.Model.Boss;
import com.minecraft_wiki.backend.Model.enums.MobStrength;
import com.minecraft_wiki.backend.Model.enums.MobType;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BossMongoRepository extends MongoRepository<Boss, ObjectId> {
    Page<Boss> findByStrength(MobStrength strength, Pageable pageable);
    Page<Boss> findByStrengthAndType(MobStrength strength, MobType type, Pageable pageable);
    Optional<Boss> findByMobIdAndStrength(ObjectId bossId, MobStrength strength);
}
