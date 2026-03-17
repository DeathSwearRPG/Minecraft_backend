package com.minecraft_wiki.backend.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class MobStats {
    private int baseDamage;
    private int damagePerLevel;

    private int baseHealth;
    private int healthPerLevel;

    private int armor;
}
