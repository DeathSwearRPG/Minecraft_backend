package com.minecraft_wiki.backend.Service;

import com.minecraft_wiki.backend.Model.BaseMob;
import com.minecraft_wiki.backend.Model.Boss;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BossService {

    private final List<Boss> bosses = List.of(
            new Boss(
                    UUID.randomUUID(),
                    "Ender Dragon",
                    200,
                    20,
                    15
            ),
            new Boss(
                    UUID.randomUUID(),
                    "Wither",
                    300,
                    30,
                    20
            ),
            new Boss(
                    UUID.randomUUID(),
                    "Warden",
                    500,
                    50,
                    30
            )
    );

    public List<Boss> getBosses() {
        return bosses;
    }

    public Boss getBossById(UUID bossId) {
        return bosses.stream()
                .filter(boss -> boss.getMobId().equals(bossId))
                .findFirst()
                .orElse(null);
    }
}
