
UPDATE mobs
SET type = 'SPIRIT'
WHERE name = 'Lich King';

UPDATE mobs
SET type = 'SPIRIT'
WHERE name = 'Crystal Warden';

UPDATE mobs
SET type = 'SPIRIT'
WHERE name = 'Frost Revenant';

UPDATE mobs
SET type = 'MONSTER'
WHERE type NOT IN ('MONSTER', 'RESISTANCE', 'PET', 'SPIRIT', 'MINION');