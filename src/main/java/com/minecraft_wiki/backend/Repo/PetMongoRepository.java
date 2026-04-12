package com.minecraft_wiki.backend.Repo;

import com.minecraft_wiki.backend.Model.Pet;
import com.minecraft_wiki.backend.Model.enums.MobType;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface PetMongoRepository extends MongoRepository<Pet, ObjectId> {
    List<Pet> findByType(MobType type);
    Optional<Pet> findByMobIdAndType(ObjectId petId, MobType type);
}
