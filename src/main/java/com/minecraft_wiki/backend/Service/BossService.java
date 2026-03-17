package com.minecraft_wiki.backend.Service;

import com.minecraft_wiki.backend.Model.BaseMob;
import com.minecraft_wiki.backend.Model.Boss;
import com.minecraft_wiki.backend.Model.MobStats;
import com.minecraft_wiki.backend.Model.enums.MobStrength;
import com.minecraft_wiki.backend.Repo.BossRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BossService {

    private final BossRepository bossRepository;

    public List<Boss> getBosses() {
        List<Boss> bosses = new ArrayList<>();

        bosses.addAll(getAllBossesFromDb());

        return bosses;
    }

    public Boss getBossById(UUID bossId) {
        return getBosses().stream()
                .filter(boss -> boss.getMobId().equals(bossId))
                .findFirst()
                .orElse(null);
    }

    public List<Boss> getAllBossesFromDb() {
        List<Object[]> rows = bossRepository.findAllBossesRaw();
        if (rows == null) {
            rows = List.of();
        }
        List<Boss> results = new ArrayList<>();

        for (Object[] r : rows) {
            List<String> extraInfoList = List.of();

            Array arr = (Array) r[12];
            if (arr != null) {
                try {
                    extraInfoList = List.of((String[]) arr.getArray());
                } catch (Exception e) {
                    extraInfoList = List.of();
                }
            }

            Boss boss = Boss.builder()
                    .mobId(UUID.fromString(r[0].toString()))
                    .name((String) r[1])
                    .description((String) r[2])
                    .imageUrl((String) r[3])
                    .locationInfo((String) r[4])
                    .extraInfo(extraInfoList)
                    .stats(MobStats.builder()
                    .baseHealth(((Number) r[9]).intValue())
                    .armor(((Number) r[11]).intValue())
                    .baseDamage(((Number) r[7]).intValue())
                    .damagePerLevel(0)
                    .healthPerLevel(0)
                    .build())
                    .build();

            results.add(boss);
        }
        return results;
    }
}
