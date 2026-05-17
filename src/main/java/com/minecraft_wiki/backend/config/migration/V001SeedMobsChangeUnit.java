package com.minecraft_wiki.backend.config.migration;

import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

@ChangeUnit(id = "v001-seed-mobs", order = "001", author = "danila")
public class V001SeedMobsChangeUnit {
    private final MongoTemplate mongoTemplate;

    public V001SeedMobsChangeUnit(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Execution
    public void execute() {
        insertIfNotExists(
                "Zombie",
                "Обычный медленный зомби",
                "http://localhost:9000/minecraft-wiki-images/mobs/43b354b8-c32b-4e8e-8bf6-3ba64ec68a2b-Zomby.jpeg",
                "Ночь, равнины",
                new MobStatsDocument(4, 1, 20, 2, 0),
                List.of("Боится солнца", "Медленно передвигается"),
                "NORMAL",
                "MONSTER"
        );

        insertIfNotExists(
                "Skeleton Archer",
                "Скелет-лучник, атакующий с расстояния",
                "https://example.com/skeleton.png",
                "Пещеры, ночь",
                new MobStatsDocument(6, 1, 18, 1, 0),
                List.of("Стреляет из лука", "Уязвим к ближнему бою"),
                "NORMAL",
                "MONSTER"
        );

        insertIfNotExists(
                "Dire Wolf",
                "Усиленный волк, опасен в стае",
                "https://example.com/direwolf.png",
                "Леса",
                new MobStatsDocument(8, 2, 30, 3, 2),
                List.of("Атакует стаей"),
                "ELITE",
                "MONSTER"
        );

        insertIfNotExists(
                "Infernal Golem",
                "Огромный огненный голем",
                "https://example.com/golem.png",
                "Вулканические пещеры",
                new MobStatsDocument(28, 0, 220, 0, 18),
                List.of("Оставляет огонь под ногами"),
                "BOSS",
                "MONSTER"
        );

        insertIfNotExists(
                "Wolf",
                "Приручаемый волк",
                "https://example.com/wolf.png",
                "Леса",
                new MobStatsDocument(5, 1, 20, 2, 0),
                List.of("Может быть приручён"),
                null,
                "PET"
        );

        insertIfNotExists(
                "Spirit Guardian",
                "Древний дух-защитник",
                "https://example.com/spirit.png",
                "Храмы",
                new MobStatsDocument(12, 2, 80, 5, 5),
                List.of("Неуязвим к обычному оружию"),
                "ELITE",
                "SPIRIT"
        );

        insertIfNotExists(
                "Summoned Minion",
                "Призванный слуга",
                "https://example.com/minion.png",
                "Создаётся магами",
                new MobStatsDocument(3, 0, 10, 0, 0),
                List.of("Исчезает со временем"),
                "WEAK",
                "MINION"
        );

        insertIfNotExists(
                "Ender dragon",
                "Главный босс измерения Края",
                "https://example.com/dragon.png",
                "Измерение Края",
                new MobStatsDocument(20, 0, 200, 0, 10),
                List.of("Регенерируется от кристаллов", "Летает"),
                "BOSS",
                "MONSTER"
        );
    }

    @RollbackExecution
    public void rollback() {
        mongoTemplate.remove(new Query(), "mobs");
    }

    private void insertIfNotExists(
            String name,
            String description,
            String imageUrl,
            String locationInfo,
            MobStatsDocument stats,
            List<String> extraInfo,
            String strength,
            String type
    ) {
        Query query = Query.query(Criteria.where("name").is(name));

        if (mongoTemplate.exists(query, "mobs")) {
            return;
        }

        MobDocument mob = new MobDocument(
                name,
                description,
                imageUrl,
                locationInfo,
                stats,
                extraInfo,
                strength,
                type
        );

        mongoTemplate.insert(mob, "mobs");
    }

    private record MobDocument(
            String name,
            String description,
            String imageUrl,
            String locationInfo,
            MobStatsDocument stats,
            List<String> extraInfo,
            String strength,
            String type
    ) {}

    private record MobStatsDocument(
            Integer baseDamage,
            Integer damagePerLevel,
            Integer baseHealth,
            Integer healthPerLevel,
            Integer armor
    ) {}
}
