package com.minecraft_wiki.backend.Service;

import com.minecraft_wiki.backend.Model.enums.MobStrength;
import com.minecraft_wiki.backend.Model.Pet;
import com.minecraft_wiki.backend.Model.enums.MobType;
import com.minecraft_wiki.backend.Repo.PetMongoRepository;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class PetService {

    private final PetMongoRepository petMongoRepository;

    public Page<Pet> getPets(String search, MobStrength strength, Pageable pageable) {
        return petMongoRepository.findPets(search, strength, pageable);
    }

    public Pet getPetById(String petId) {
        try {
            ObjectId objectId = new ObjectId(petId);

            return petMongoRepository.findByMobIdAndType(objectId, MobType.PET)
                    .orElseThrow(() -> new RuntimeException("pet doesn't exist " + petId));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid pet id format: " + petId);
        }
    }
}
