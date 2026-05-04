package com.minecraft_wiki.backend.Repo;

import com.minecraft_wiki.backend.Model.Pet;
import com.minecraft_wiki.backend.Model.enums.MobStrength;
import com.minecraft_wiki.backend.Model.enums.MobType;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PetMongoRepository extends MongoRepository<Pet, ObjectId> {
    Page<Pet> findByType(MobType type, Pageable pageable);
    Page<Pet> findByTypeAndStrength(MobType type, MobStrength strength, Pageable pageable);
    Optional<Pet> findByMobIdAndType(ObjectId petId, MobType type);
}
