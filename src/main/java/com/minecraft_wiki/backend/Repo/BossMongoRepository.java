package com.minecraft_wiki.backend.Repo;

import com.minecraft_wiki.backend.Model.Boss;
import com.minecraft_wiki.backend.Model.enums.MobStrength;
import com.minecraft_wiki.backend.Model.enums.MobType;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface BossMongoRepository extends MongoRepository<Boss, ObjectId> {
    List<Boss> findByStrength(MobStrength strength);
    List<Boss> findByStrengthAndType(MobStrength strength, MobType type);
    Optional<Boss> findByMobIdAndStrength(ObjectId bossId, MobStrength strength);
}
