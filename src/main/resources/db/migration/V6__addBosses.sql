INSERT INTO mobs (
    name,
    description,
    image_url,
    location_info,
    strength,
    type
)
VALUES (
           'Infernal Golem',
           'Огромный голем из раскалённого камня и магмы, охраняющий древние кузницы.',
           'https://example.com/images/mobs/infernal_golem.png',
           'Заброшенные кузницы, вулканические пещеры, подземные храмы огня',
           'BOSS',
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
    220,
    0,
    28,
    0,
    18
FROM mobs
WHERE name = 'Infernal Golem';

INSERT INTO mob_extra_info (mob_id, info)
SELECT id, 'Faction: Fire Lords'
FROM mobs
WHERE name = 'Infernal Golem';

INSERT INTO mob_extra_info (mob_id, info)
SELECT id, 'Ability: Leaves burning ground after удар'
FROM mobs
WHERE name = 'Infernal Golem';

INSERT INTO mob_drops (mob_id, item_name, min_count, max_count, chance)
SELECT id, 'molten_core', 1, 2, 0.65
FROM mobs
WHERE name = 'Infernal Golem';

INSERT INTO mob_drops (mob_id, item_name, min_count, max_count, chance)
SELECT id, 'obsidian_plate', 2, 4, 0.45
FROM mobs
WHERE name = 'Infernal Golem';

INSERT INTO mobs (
    name,
    description,
    image_url,
    location_info,
    strength,
    type
)
VALUES (
           'Lich King',
           'Древний повелитель нежити, сохранивший разум и тёмную магию после смерти.',
           'https://example.com/images/mobs/lich_king.png',
           'Проклятые крепости, ледяные мавзолеи, некрополи',
           'BOSS',
           'UNDEAD'
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
    180,
    0,
    24,
    0,
    10
FROM mobs
WHERE name = 'Lich King';

INSERT INTO mob_extra_info (mob_id, info)
SELECT id, 'Faction: Undead Legion'
FROM mobs
WHERE name = 'Lich King';

INSERT INTO mob_extra_info (mob_id, info)
SELECT id, 'Ability: Summons skeleton warriors'
FROM mobs
WHERE name = 'Lich King';

INSERT INTO mob_extra_info (mob_id, info)
SELECT id, 'Ability: Casts curse projectiles'
FROM mobs
WHERE name = 'Lich King';

INSERT INTO mob_drops (mob_id, item_name, min_count, max_count, chance)
SELECT id, 'lich_bone_staff', 1, 1, 0.25
FROM mobs
WHERE name = 'Lich King';

INSERT INTO mob_drops (mob_id, item_name, min_count, max_count, chance)
SELECT id, 'dark_essence', 2, 5, 0.70
FROM mobs
WHERE name = 'Lich King';

INSERT INTO mobs (
    name,
    description,
    image_url,
    location_info,
    strength,
    type
)
VALUES (
           'Broodmother',
           'Гигантская паучиха, управляющая целыми роями ядовитых детёнышей.',
           'https://example.com/images/mobs/broodmother.png',
           'Глубокие пещеры, паучьи гнёзда, лесные провалы',
           'BOSS',
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
    160,
    0,
    20,
    0,
    8
FROM mobs
WHERE name = 'Broodmother';

INSERT INTO mob_extra_info (mob_id, info)
SELECT id, 'Faction: Spider Brood'
FROM mobs
WHERE name = 'Broodmother';

INSERT INTO mob_extra_info (mob_id, info)
SELECT id, 'Ability: Summons spiderlings during battle'
FROM mobs
WHERE name = 'Broodmother';

INSERT INTO mob_extra_info (mob_id, info)
SELECT id, 'Ability: Applies poison with melee attacks'
FROM mobs
WHERE name = 'Broodmother';

INSERT INTO mob_drops (mob_id, item_name, min_count, max_count, chance)
SELECT id, 'venom_gland', 1, 2, 0.60
FROM mobs
WHERE name = 'Broodmother';

INSERT INTO mob_drops (mob_id, item_name, min_count, max_count, chance)
SELECT id, 'silk_bundle', 3, 6, 0.80
FROM mobs
WHERE name = 'Broodmother';

INSERT INTO mobs (
    name,
    description,
    image_url,
    location_info,
    strength,
    type
)
VALUES (
           'Crystal Warden',
           'Таинственный страж, созданный из древних кристаллов и магической энергии.',
           'https://example.com/images/mobs/crystal_warden.png',
           'Кристальные шахты, магические руины, подземные святилища',
           'BOSS',
           'MAGICAL'
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
    200,
    0,
    26,
    0,
    14
FROM mobs
WHERE name = 'Crystal Warden';

INSERT INTO mob_extra_info (mob_id, info)
SELECT id, 'Faction: Arcane Guardians'
FROM mobs
WHERE name = 'Crystal Warden';

INSERT INTO mob_extra_info (mob_id, info)
SELECT id, 'Ability: Reflects part of ranged damage'
FROM mobs
WHERE name = 'Crystal Warden';

INSERT INTO mob_extra_info (mob_id, info)
SELECT id, 'Ability: Fires crystal shards in all directions'
FROM mobs
WHERE name = 'Crystal Warden';

INSERT INTO mob_drops (mob_id, item_name, min_count, max_count, chance)
SELECT id, 'arcane_crystal', 2, 4, 0.75
FROM mobs
WHERE name = 'Crystal Warden';

INSERT INTO mob_drops (mob_id, item_name, min_count, max_count, chance)
SELECT id, 'warden_core', 1, 1, 0.35
FROM mobs
WHERE name = 'Crystal Warden';

INSERT INTO mobs (
    name,
    description,
    image_url,
    location_info,
    strength,
    type
)
VALUES (
           'Frost Revenant',
           'Замёрзший дух павшего воина, возвращённый к жизни холодной магией.',
           'https://example.com/images/mobs/frost_revenant.png',
           'Ледяные пещеры, снежные пустоши, древние курганы',
           'BOSS',
           'UNDEAD'
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
    190,
    0,
    22,
    0,
    11
FROM mobs
WHERE name = 'Frost Revenant';

INSERT INTO mob_extra_info (mob_id, info)
SELECT id, 'Faction: Frozen Dead'
FROM mobs
WHERE name = 'Frost Revenant';

INSERT INTO mob_extra_info (mob_id, info)
SELECT id, 'Ability: Slows enemies with attacks'
FROM mobs
WHERE name = 'Frost Revenant';

INSERT INTO mob_extra_info (mob_id, info)
SELECT id, 'Ability: Periodically creates ice spikes'
FROM mobs
WHERE name = 'Frost Revenant';

INSERT INTO mob_drops (mob_id, item_name, min_count, max_count, chance)
SELECT id, 'frozen_heart', 1, 1, 0.40
FROM mobs
WHERE name = 'Frost Revenant';

INSERT INTO mob_drops (mob_id, item_name, min_count, max_count, chance)
SELECT id, 'ice_shard', 2, 5, 0.70
FROM mobs
WHERE name = 'Frost Revenant';