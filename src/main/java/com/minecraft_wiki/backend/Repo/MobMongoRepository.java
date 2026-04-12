package com.minecraft_wiki.backend.Repo;

import com.minecraft_wiki.backend.Model.Mob;
import com.minecraft_wiki.backend.Model.enums.MobStrength;
import com.minecraft_wiki.backend.Model.enums.MobType;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MobMongoRepository extends MongoRepository<Mob, ObjectId> {

    List<Mob> findByStrength(MobStrength strength);
    List<Mob> findByType(MobType type);
    List<Mob> findByStrengthAndType(MobStrength strength, MobType type);

}
