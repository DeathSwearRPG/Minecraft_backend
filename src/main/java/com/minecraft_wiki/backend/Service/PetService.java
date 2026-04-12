package com.minecraft_wiki.backend.Service;

import com.minecraft_wiki.backend.Model.enums.MobStrength;
import com.minecraft_wiki.backend.Model.Pet;
import com.minecraft_wiki.backend.Model.enums.MobType;
import com.minecraft_wiki.backend.Repo.PetMongoRepository;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor

public class PetService {

    private final PetMongoRepository petMongoRepository;

    public List<Pet> getPets() {
        return petMongoRepository.findByType(MobType.PET);
    }

    public Pet getPetById(String petId) {
        try {
            ObjectId objectId = new ObjectId(petId);

            return petMongoRepository.findByMobIdAndType(objectId, MobType.PET)
                    .orElseThrow(() -> new RuntimeException("boss doesn't exist " + petId));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid boss id format: " + petId);
        }
    }
}
