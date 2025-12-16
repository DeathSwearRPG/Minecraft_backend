package com.minecraft_wiki.backend.Service;

import com.minecraft_wiki.backend.Model.BaseMob;
import com.minecraft_wiki.backend.Model.Boss;
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

            Boss boss = new Boss(
                    UUID.fromString(r[0].toString()),
                    (String) r[1],
                    ((Number) r[9]).intValue(),
                    ((Number) r[11]).intValue(),
                    ((Number) r[7]).intValue()
            );

            boss.setDescription((String) r[2]);
            boss.setImageUrl((String) r[3]);
            boss.setLocationInfo((String) r[4]);

            Array arr = (Array) r[12];
            if (arr != null) {
                try {
                    boss.setExtraInfo(List.of((String[]) arr.getArray()));
                } catch (Exception e) {
                    boss.setExtraInfo(List.of());
                }
            }

            results.add(boss);
        }
        return results;
    }
}
