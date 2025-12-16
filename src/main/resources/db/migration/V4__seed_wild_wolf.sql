INSERT INTO mobs (
    name,
    description,
    image_url,
    location_info,
    strength,
    type
)
VALUES (
           'Wild Wolf',
           'Агрессивный дикий волк, обитающий в лесах и горах.',
           'https://example.com/images/mobs/wild_wolf.png',
           'Леса, горные биомы, окраины деревень',
           'NORMAL',
           'MONSTER'
       );

INSERT INTO mob_stats (
    mob_id,
    base_health,
    damage_per_level,
    base_damage,
    health_per_level,
    armor
)
SELECT
    id,
    15,
    0,
    3,
    0,
    0
FROM mobs
WHERE name = 'Wild Wolf';

INSERT INTO mob_extra_info (mob_id, info)
SELECT id, 'Faction: MonsterRPG'
FROM mobs
WHERE name = 'Wild Wolf';

INSERT INTO mob_drops (mob_id, item_name, min_count, max_count, chance)
SELECT id, 'string', 1, 3, 0.4
FROM mobs
WHERE name = 'Wild Wolf';

INSERT INTO mob_drops (mob_id, item_name, min_count, max_count, chance)
SELECT id, 'RitualSkull', 1, 1, 0.01
FROM mobs
WHERE name = 'Wild Wolf';

INSERT INTO mob_drops (mob_id, item_name, min_count, max_count, chance)
SELECT id, 'WeirdBone', 1, 2, 0.3
FROM mobs
WHERE name = 'Wild Wolf';