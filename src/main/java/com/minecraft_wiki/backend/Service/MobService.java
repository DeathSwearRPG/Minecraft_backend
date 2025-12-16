package com.minecraft_wiki.backend.Service;

import com.minecraft_wiki.backend.Model.BaseMob;
import com.minecraft_wiki.backend.Model.Boss;
import com.minecraft_wiki.backend.Model.Mob;
import com.minecraft_wiki.backend.Model.enums.MobStrength;
import com.minecraft_wiki.backend.Model.enums.MobType;
import com.minecraft_wiki.backend.Repo.MobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class MobService {

    private final MobRepository mobRepository;

    public List<BaseMob> getAllMobs(MobStrength strength, MobType type) {
        return loadMobsFromDb(strength, type);
    }

    public BaseMob getMobById(UUID id) {
        return loadMobsFromDb().stream()
                .filter(m -> m.getMobId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<BaseMob> loadMobsFromDb(
            MobStrength strength,
            MobType type
    ) {
        List<Object[]> rows = mobRepository.findMobsRaw(
                strength != null ? strength.name() : null,
                type != null ? type.name() : null
        );

        if (rows == null) {
            rows = List.of();
        }

        List<BaseMob> result = new ArrayList<>();

        for (Object[] r : rows) {

            BaseMob mob = new Mob(
                    UUID.fromString(r[0].toString()),
                    (String) r[1],
                    ((Number) r[9]).intValue(),
                    ((Number) r[11]).intValue(),
                    ((Number) r[7]).intValue()
            );

            mob.setDescription((String) r[2]);
            mob.setImageUrl((String) r[3]);
            mob.setLocationInfo((String) r[4]);
            mob.setStrength(r[5] != null ? MobStrength.valueOf(r[5].toString()) : null);
            mob.setType(r[6] != null ? MobType.valueOf(r[6].toString()) : null);

            Array sqlArray = (Array) r[12];
            try {
                mob.setExtraInfo(
                        sqlArray != null
                                ? List.of((String[]) sqlArray.getArray())
                                : List.of()
                );
            } catch (SQLException e) {
                throw new RuntimeException("Failed to read mob extraInfo", e);
            }

            result.add(mob);
        }

        return result;

    }

    public List<BaseMob> loadMobsFromDb() {
        return loadMobsFromDb(null, null);
    }
}

