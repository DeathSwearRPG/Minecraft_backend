package com.minecraft_wiki.backend.Repo;

import com.minecraft_wiki.backend.Model.Pet;
import com.minecraft_wiki.backend.Model.enums.MobType;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PetMongoRepository extends MongoRepository<Pet, ObjectId>, PetCustomRepository {
    Optional<Pet> findByMobIdAndType(ObjectId petId, MobType type);
}
