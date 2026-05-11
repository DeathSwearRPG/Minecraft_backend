package com.minecraft_wiki.backend.Repo;

import com.minecraft_wiki.backend.Model.Item;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemMongoRepository extends MongoRepository<Item, ObjectId>, ItemCustomRepository {
}
