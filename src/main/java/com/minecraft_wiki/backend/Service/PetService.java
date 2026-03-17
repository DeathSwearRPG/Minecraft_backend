package com.minecraft_wiki.backend.Service;

import com.minecraft_wiki.backend.Model.enums.MobStrength;
import com.minecraft_wiki.backend.Model.Pet;
import com.minecraft_wiki.backend.Model.enums.MobType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PetService {
    private final List<Pet> pets = List.of(
            Pet.builder()
                    .mobId(UUID.randomUUID())
                    .name("Wolf")
                    .description("A loyal companion that attacks hostile mobs.")
                    .strength(MobStrength.NORMAL)
                    .build(),

            Pet.builder()
                    .mobId(UUID.randomUUID())
                    .name("Cat")
                    .description("A friendly pet that scares away creepers.")
                    .strength(MobStrength.NORMAL)
                    .build(),

            Pet.builder()
                    .mobId(UUID.randomUUID())
                    .name("Parrot")
                    .description("A colorful pet that mimics nearby mob sounds.")
                    .strength(MobStrength.WEAK)
                    .build(),

            Pet.builder()
                    .mobId(UUID.randomUUID())
                    .name("Iron Golem")
                    .description("A large protector mob that defends villages.")
                    .strength(MobStrength.NORMAL)
                    .build(),

            Pet.builder()
                    .mobId(UUID.randomUUID())
                    .name("Snow Golem")
                    .description("A living snowman that throws snowballs.")
                    .strength(MobStrength.WEAK)
                    .build()
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
