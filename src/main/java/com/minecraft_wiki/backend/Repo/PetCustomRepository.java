package com.minecraft_wiki.backend.Repo;

import com.minecraft_wiki.backend.Model.Pet;
import com.minecraft_wiki.backend.Model.enums.MobStrength;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PetCustomRepository {
    Page<Pet> findPets(
            String search,
            MobStrength strength,
            Pageable pageable
    );
}
