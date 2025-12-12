package com.minecraft_wiki.backend.Service;

import com.minecraft_wiki.backend.Model.enums.MobStrength;
import com.minecraft_wiki.backend.Model.Pet;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PetService {
    private final List<Pet> pets = List.of(
            new Pet(
                    UUID.randomUUID(),
                    "Wolf",
                    "A loyal companion that attacks hostile mobs.",
                    MobStrength.NORMAL
            ),
            new Pet(
                    UUID.randomUUID(),
                    "Cat",
                    "A friendly pet that scares away creepers.",
                    MobStrength.WEAK
            ),
            new Pet(
                    UUID.randomUUID(),
                    "Parrot",
                    "A colorful pet that mimics nearby mob sounds.",
                    MobStrength.ELITE
            ),
            new Pet(
                    UUID.randomUUID(),
                    "Iron Golem",
                    "A large protector mob that defends villages.",
                    MobStrength.NORMAL
            ),
            new Pet(
                    UUID.randomUUID(),
                    "Snow Golem",
                    "A living snowman that throws snowballs.",
                    MobStrength.ELITE
            )
    );

    public List<Pet> getPets() {
        return pets;
    }

    public Pet getPetById(UUID petId) {
        return pets.stream()
                .filter(pet -> pet.getMobId().equals(petId))
                .findFirst()
                .orElse(null);
    }
}
