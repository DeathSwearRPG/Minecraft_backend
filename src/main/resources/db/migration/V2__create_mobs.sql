CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE mobs (
                      id UUID PRIMARY KEY DEFAULT gen_random_uuid(),

                      name TEXT NOT NULL,
                      description TEXT,
                      image_url TEXT,
                      location_info TEXT,

                      strength VARCHAR(20),
                      type VARCHAR(20)
);

CREATE TABLE mob_stats (
                           mob_id UUID PRIMARY KEY REFERENCES mobs(id) ON DELETE CASCADE,

                           base_damage INT NOT NULL,
                           damage_per_level INT NOT NULL,

                           base_health INT NOT NULL,
                           health_per_level INT NOT NULL,

                           armor INT NOT NULL
);

CREATE TABLE mob_drops (
                           id UUID PRIMARY KEY DEFAULT gen_random_uuid(),

                           mob_id UUID NOT NULL REFERENCES mobs(id) ON DELETE CASCADE,

                           item_name TEXT NOT NULL,

                           min_count INT NOT NULL,
                           max_count INT NOT NULL,

                           chance DOUBLE PRECISION NOT NULL
);

CREATE TABLE mob_extra_info (
                                id UUID PRIMARY KEY DEFAULT gen_random_uuid(),

                                mob_id UUID NOT NULL REFERENCES mobs(id) ON DELETE CASCADE,

                                info TEXT NOT NULL
);