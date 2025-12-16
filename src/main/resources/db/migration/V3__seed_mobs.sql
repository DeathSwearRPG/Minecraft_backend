
INSERT INTO mobs (name, description, image_url, location_info, strength, type)
VALUES (
           'Zombie',
           'Обычный зомби',
           'https://example.com/zombie.png',
           'Появляется ночью',
           'NORMAL',
           'MONSTER'
       );

INSERT INTO mob_stats (mob_id, base_damage, damage_per_level, base_health, health_per_level, armor)
SELECT id, 4, 1, 20, 2, 0
FROM mobs
WHERE name = 'Zombie';

INSERT INTO mob_extra_info (mob_id, info)
SELECT id, 'Боится солнца'
FROM mobs
WHERE name = 'Zombie';

INSERT INTO mob_extra_info (mob_id, info)
SELECT id, 'Медленно передвигается'
FROM mobs
WHERE name = 'Zombie';


-- ---------- MOB 2: Dragon Boss ----------
INSERT INTO mobs (name, description, image_url, location_info, strength, type)
VALUES (
           'Ender Dragon',
           'Главный босс измерения Края',
           'https://example.com/dragon.png',
           'Измерение Края',
           'BOSS',
           NULL
       );

INSERT INTO mob_stats (mob_id, base_damage, damage_per_level, base_health, health_per_level, armor)
SELECT id, 20, 5, 200, 10, 10
FROM mobs
WHERE name = 'Ender Dragon';

INSERT INTO mob_extra_info (mob_id, info)
SELECT id, 'Летает'
FROM mobs
WHERE name = 'Ender Dragon';

INSERT INTO mob_extra_info (mob_id, info)
SELECT id, 'Регенерируется от кристаллов'
FROM mobs
WHERE name = 'Ender Dragon';


INSERT INTO mobs (name, description, image_url, location_info, strength, type)
VALUES (
           'Wolf',
           'Приручаемый волк',
           'https://example.com/wolf.png',
           'Лесные биомы',
           NULL,
           'PET'
       );

INSERT INTO mob_stats (mob_id, base_damage, damage_per_level, base_health, health_per_level, armor)
SELECT id, 5, 1, 20, 2, 0
FROM mobs
WHERE name = 'Wolf';

INSERT INTO mob_extra_info (mob_id, info)
SELECT id, 'Может быть приручён'
FROM mobs
WHERE name = 'Wolf';
