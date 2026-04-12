package com.minecraft_wiki.backend.Repo;

import com.minecraft_wiki.backend.Model.Boss;
import com.minecraft_wiki.backend.Model.enums.MobStrength;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface BossMongoRepository extends MongoRepository<Boss, ObjectId> {
    List<Boss> findByStrength(MobStrength strength);
    Optional<Boss> findByMobIdAndStrength(ObjectId bossId, MobStrength strength);
}
