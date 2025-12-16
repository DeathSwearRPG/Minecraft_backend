package com.minecraft_wiki.backend.Repo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BossRepository {
    private final JdbcTemplate jdbcTemplate;

    public BossRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Object[]> findAllBossesRaw() {

        return jdbcTemplate.query(
                """
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
                WHERE m.strength = 'BOSS'
                GROUP BY
                    m.id, m.name, m.description, m.image_url,
                    m.location_info, m.strength, m.type,
                    s.base_damage, s.damage_per_level,
                    s.base_health, s.health_per_level, s.armor
                ORDER BY m.name
                """,
                (rs, rowNum) -> new Object[] {
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
                }
        );
    }
}
