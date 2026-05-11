package com.minecraft_wiki.backend.Repo;

import com.minecraft_wiki.backend.Model.Mob;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MobMongoRepository extends MongoRepository<Mob, ObjectId>, MobCustomRepository {
}
