package com.minecraft_wiki.backend.Service;

import com.minecraft_wiki.backend.Model.Boss;
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
                    "Aggressive when owner is hurt, follows player.",
                    20,
                    4,
                    4
            ),
            new Pet(
                    UUID.randomUUID(),
                    "Cat",
                    "A friendly pet that scares away creepers.",
                    "Independent, avoids danger, follows when tamed.",
                    10,
                    0,
                    2
            ),
            new Pet(
                    UUID.randomUUID(),
                    "Parrot",
                    "A colorful pet that mimics nearby mob sounds.",
                    "Passive, perches on player's shoulder.",
                    6,
                    0,
                    1
            ),
            new Pet(
                    UUID.randomUUID(),
                    "Iron Golem",
                    "A large protector mob that defends villages.",
                    "Neutral unless provoked, protects owner.",
                    100,
                    12,
                    15
            ),
            new Pet(
                    UUID.randomUUID(),
                    "Snow Golem",
                    "A living snowman that throws snowballs.",
                    "Ranged attacks, creates snow layers.",
                    4,
                    0,
                    1
            )
    );

    public List<Pet> getPets() {
        return pets;
    }

    public Pet getPetById(UUID petId) {
        return pets.stream()
                .filter(pet -> pet.getPetId().equals(petId))
                .findFirst()
                .orElse(null);
    }
}
