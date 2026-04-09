CREATE SCHEMA IF NOT EXISTS game;

CREATE TABLE game.items (
                            id UUID PRIMARY KEY,
                            name TEXT NOT NULL,
                            description TEXT,
                            image_url TEXT,

                            item_type TEXT NOT NULL,
                            item_rarity TEXT NOT NULL,
                            special_characteristic TEXT,

                            item_stats JSONB,
                            craft_recipe JSONB,

                            created_at TIMESTAMP DEFAULT now()
);
