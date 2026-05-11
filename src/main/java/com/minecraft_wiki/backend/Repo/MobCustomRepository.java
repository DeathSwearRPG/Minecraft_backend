package com.minecraft_wiki.backend.Repo;

import com.minecraft_wiki.backend.Model.Mob;
import com.minecraft_wiki.backend.Model.enums.MobStrength;
import com.minecraft_wiki.backend.Model.enums.MobType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MobCustomRepository {
    Page<Mob> findMobs(
            String search,
            MobStrength strength,
            MobType type,
            Pageable pageable
    );
}
