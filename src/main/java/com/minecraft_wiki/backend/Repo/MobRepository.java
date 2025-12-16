package com.minecraft_wiki.backend.Repo;

import lombok.AllArgsConstructor;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class MobRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<Object[]> findMobsRaw(
            String strength,
            String type
    ) {
        String sql = """
        SELECT
            m.id,
            m.name,
            m.description,
            m.image_url,
            m.location_info,
            m.strength,
            m.type,
            s.base_damage,
            s.damage_per_level,
            s.base_health,
            s.health_per_level,
            s.armor,
            array_agg(e.info) AS extra_info
        FROM mobs m
        JOIN mob_stats s ON s.mob_id = m.id
        LEFT JOIN mob_extra_info e ON e.mob_id = m.id
        WHERE 1 = 1
    """;

        List<Object> params = new ArrayList<>();

        if (strength != null) {
            sql += " AND m.strength = ?";
            params.add(strength);
        }

        if (type != null) {
            sql += " AND m.type = ?";
            params.add(type);
        }

        sql += """
        GROUP BY
            m.id, m.name, m.description, m.image_url,
            m.location_info, m.strength, m.type,
            s.base_damage, s.damage_per_level,
            s.base_health, s.health_per_level, s.armor
        ORDER BY m.name
    """;

        return jdbcTemplate.query(sql, params.toArray(), ROW_MAPPER);
    }

    private static final RowMapper<Object[]> ROW_MAPPER = (rs, rowNum) -> new Object[]{
            rs.getObject("id"),
            rs.getString("name"),
            rs.getString("description"),
            rs.getString("image_url"),
            rs.getString("location_info"),
            rs.getString("strength"),
            rs.getString("type"),
            rs.getInt("base_damage"),
            rs.getInt("damage_per_level"),
            rs.getInt("base_health"),
            rs.getInt("health_per_level"),
            rs.getInt("armor"),
            rs.getArray("extra_info")
    };

    public List<Object[]> findAllMobsRaw() {
        return findMobsRaw(null, null);
    }
}
