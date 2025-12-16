INSERT INTO mobs (
    name,
    description,
    image_url,
    location_info,
    strength,
    type
)
VALUES (
           'Rat',
           'Мелкая и быстрая крыса, обитающая в подземельях и руинах.',
           'https://example.com/images/mobs/rat.png',
           'Подземелья, катакомбы, заброшенные постройки',
           'WEAK',
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
    10,
    0,
    2,
    0,
    0
FROM mobs
WHERE name = 'Rat';

INSERT INTO mob_extra_info (mob_id, info)
SELECT id, 'Faction: MonsterRPG'
FROM mobs
WHERE name = 'Rat';

INSERT INTO mob_drops (mob_id, item_name, min_count, max_count, chance)
SELECT id, 'rotten_flesh', 1, 1, 0.3
FROM mobs
WHERE name = 'Rat';