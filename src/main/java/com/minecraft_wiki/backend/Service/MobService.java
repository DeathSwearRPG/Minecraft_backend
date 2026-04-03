package com.minecraft_wiki.backend.Service;

import com.minecraft_wiki.backend.Model.Mob;
import com.minecraft_wiki.backend.Model.MobStats;
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

    public List<Mob> getAllMobs(MobStrength strength, MobType type) {
        return loadMobsFromDb(strength, type);
    }

    public Mob getMobById(UUID mobId) {
        return loadMobsFromDb().stream()
                .filter(m -> m.getMobId().equals(mobId))
                .findFirst()
                .orElse(null);
    }

    public List<Mob> loadMobsFromDb(
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

        List<Mob> result = new ArrayList<>();

        for (Object[] r : rows) {

            Mob mob = Mob.builder()
                    .mobId(UUID.fromString(r[0].toString()))
                    .name((String) r[1])
                    .description((String) r[2])
                    .imageUrl((String) r[3])
                    .locationInfo((String) r[4])
                    .strength(r[5] != null ? MobStrength.valueOf(r[5].toString()) : null)
                    .type(r[6] != null ? MobType.valueOf(r[6].toString()) : null)
                    .extraInfo(readExtraInfo(r[12]))
                    .stats(MobStats.builder()
                            .baseHealth(((Number) r[9]).intValue())
                            .armor(((Number) r[11]).intValue())
                            .baseDamage(((Number) r[7]).intValue())
                            .damagePerLevel(0)
                            .healthPerLevel(0)
                            .build())
                    .build();

            result.add(mob);
        }

        return result;

    }

    private List<String> readExtraInfo(Object value) {
        if (value == null) {
            return List.of();
        }

        Array sqlArray = (Array) value;
        try {
            return List.of((String[]) sqlArray.getArray());
        } catch (SQLException e) {
            throw new RuntimeException("Failed to read mob extraInfo", e);
        }
    }

    public List<Mob> loadMobsFromDb() {
        return loadMobsFromDb(null, null);
    }
}

