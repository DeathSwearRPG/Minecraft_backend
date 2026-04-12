package com.minecraft_wiki.backend.Repo;

import com.minecraft_wiki.backend.Model.GameClass;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GameClassMongoRepository extends MongoRepository<GameClass, ObjectId> {

}
