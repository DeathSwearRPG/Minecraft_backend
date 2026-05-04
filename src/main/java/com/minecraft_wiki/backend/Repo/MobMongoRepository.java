package com.minecraft_wiki.backend.Repo;

import com.minecraft_wiki.backend.Model.Mob;
import com.minecraft_wiki.backend.Model.enums.MobStrength;
import com.minecraft_wiki.backend.Model.enums.MobType;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.data.domain.Pageable;

public interface MobMongoRepository extends MongoRepository<Mob, ObjectId> {

    Page<Mob> findByStrength(MobStrength strength, Pageable pageable);
    Page<Mob> findByType(MobType type, Pageable pageable);
    Page<Mob> findByStrengthAndType(MobStrength strength, MobType type, Pageable pageable);

}
