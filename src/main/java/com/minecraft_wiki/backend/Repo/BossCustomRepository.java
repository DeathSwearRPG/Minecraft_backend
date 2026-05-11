package com.minecraft_wiki.backend.Repo;

import com.minecraft_wiki.backend.Model.Boss;
import com.minecraft_wiki.backend.Model.enums.MobType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BossCustomRepository {
    Page<Boss> findBosses(
            String search,
            MobType type,
            Pageable pageable
    );
}
