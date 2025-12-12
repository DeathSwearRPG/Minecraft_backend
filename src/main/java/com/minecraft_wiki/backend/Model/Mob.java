package com.minecraft_wiki.backend.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data

public class Mob extends BaseMob {
    public Mob(UUID mobId,
               String name,
               int health,
               int armor,
               int damage) {

        this.mobId = mobId;
        this.name = name;

        this.stats = new MobStats(
                damage,
                0,
                health,
                0,
                armor
        );

        this.drops = List.of();
        this.extraInfo = List.of();
    }

}
