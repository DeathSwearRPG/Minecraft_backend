package com.minecraft_wiki.backend.Model;

import com.minecraft_wiki.backend.Model.enums.MobStrength;
import com.minecraft_wiki.backend.Model.enums.MobType;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data

public class Pet extends BaseMob {
    {
        this.type = MobType.PET;
    }

    // Используем удобный конструктор как в BossService
    public Pet(UUID mobId,
               String name,
               String description,
               MobStrength strength) {

        this.mobId = mobId;
        this.name = name;
        this.description = description;
        this.strength = strength;

        this.stats = new MobStats(
                5,   // damage
                1,   // damagePerLevel
                20,  // health
                2,   // health per level
                0    // armor
        );

        this.drops = List.of();
        this.extraInfo = List.of();
    }
}
