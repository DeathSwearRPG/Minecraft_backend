package com.minecraft_wiki.backend.config.migration;

import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.Map;

@ChangeUnit(id = "v004-seed-game-classes", order = "004", author = "danila")
public class V004SeedGameClassesChangeUnit {

    private final MongoTemplate mongoTemplate;

    public V004SeedGameClassesChangeUnit(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Execution
    public void execute() {
        insertIfNotExists(
                new GameClassDocument(
                        "Берсерк",
                        "Воин, черпающий силу в сражениях. Обладает высоким уроном и регенерацией здоровья в бою.",
                        "https://example.com/classes/berserk.png",
                        List.of(
                                new ClassSkillDocument(
                                        new ObjectId(),
                                        "Жажда крови",
                                        List.of(
                                                "После нанесения урона врагу есть 30% шанс",
                                                "восстановить себе {heal} здоровья.",
                                                "Перезарядка: {cooldown} секунд"
                                        ),
                                        "REDSTONE",
                                        "ATTACK",
                                        "https://example.com/skills/berserk_attack.gif",
                                        new SkillProgressionDocument(
                                                1,
                                                30,
                                                Map.of(
                                                        "heal", new StatGrowthDocument(0.5, 0.3, null, null),
                                                        "cooldown", new StatGrowthDocument(10.0, -0.5, 7.0, null)
                                                )
                                        )
                                )
                        )
                )
        );

        insertIfNotExists(
                new GameClassDocument(
                        "Ледяной маг",
                        "Маг, способный замораживать врагов и контролировать поле боя.",
                        "https://example.com/classes/ice_mage.png",
                        List.of(
                                new ClassSkillDocument(
                                        new ObjectId(),
                                        "Ледяная стрела",
                                        List.of(
                                                "Вы выпускаете ледяной снаряд,",
                                                "наносящий {damage} урона и",
                                                "замедляющий врага на {duration} секунд.",
                                                "Перезарядка: {cooldown} секунд"
                                        ),
                                        "ICE",
                                        "ACTIVE",
                                        "https://example.com/skills/ice_arrow.gif",
                                        new SkillProgressionDocument(
                                                1,
                                                30,
                                                Map.of(
                                                        "damage", new StatGrowthDocument(4.0, 0.8, null, null),
                                                        "duration", new StatGrowthDocument(2.0, 0.2, null, 6.0),
                                                        "cooldown", new StatGrowthDocument(8.0, -0.2, 4.0, null)
                                                )
                                        )
                                ),
                                new ClassSkillDocument(
                                        new ObjectId(),
                                        "Ледяная тюрьма",
                                        List.of(
                                                "Вы заключаете врагов в ледяную ловушку,",
                                                "обездвиживая их на {duration} секунд.",
                                                "Перезарядка: {cooldown} секунд"
                                        ),
                                        "PACKED_ICE",
                                        "ACTIVE",
                                        "https://example.com/skills/ice_prison.gif",
                                        new SkillProgressionDocument(
                                                5,
                                                20,
                                                Map.of(
                                                        "duration", new StatGrowthDocument(3.0, 0.5, null, 10.0),
                                                        "cooldown", new StatGrowthDocument(25.0, -0.7, 12.0, null)
                                                )
                                        )
                                )
                        )
                )
        );

        insertIfNotExists(
                new GameClassDocument(
                        "Некромант",
                        "Маг, поклоняющийся Смерти и управляющий силами тьмы.",
                        "https://example.com/classes/necromancer.png",
                        List.of(
                                new ClassSkillDocument(
                                        new ObjectId(),
                                        "Поднятие мёртвых",
                                        List.of(
                                                "Вы воскрешаете падших врагов,",
                                                "призывая {minions} прислужников.",
                                                "Длительность: {duration} секунд."
                                        ),
                                        "WITHER_SKELETON_SKULL",
                                        "ACTIVE",
                                        "https://example.com/skills/raise_dead.gif",
                                        new SkillProgressionDocument(
                                                1,
                                                15,
                                                Map.of(
                                                        "minions", new StatGrowthDocument(1.0, 0.3, null, 5.0),
                                                        "duration", new StatGrowthDocument(10.0, 2.0, null, 40.0)
                                                )
                                        )
                                ),
                                new ClassSkillDocument(
                                        new ObjectId(),
                                        "Проклятие разложения",
                                        List.of(
                                                "Вы накладываете проклятие на цель,",
                                                "нанося {damage} урона каждую секунду.",
                                                "Перезарядка: {cooldown} секунд."
                                        ),
                                        "WITHER_ROSE",
                                        "ATTACK",
                                        "https://example.com/skills/decay_curse.gif",
                                        new SkillProgressionDocument(
                                                3,
                                                30,
                                                Map.of(
                                                        "damage", new StatGrowthDocument(2.0, 0.4, null, null),
                                                        "cooldown", new StatGrowthDocument(12.0, -0.3, 6.0, null)
                                                )
                                        )
                                )
                        )
                )
        );
    }

    @RollbackExecution
    public void rollback() {
        mongoTemplate.remove(Query.query(Criteria.where("name").in(
                "Берсерк",
                "Ледяной маг",
                "Некромант"
        )), "gameClasses");
    }

    private void insertIfNotExists(GameClassDocument gameClass) {
        Query query = Query.query(Criteria.where("name").is(gameClass.name()));

        if (mongoTemplate.exists(query, "gameClasses")) {
            return;
        }

        mongoTemplate.insert(gameClass, "gameClasses");
    }

    private record GameClassDocument(
            String name,
            String description,
            String imageUrl,
            List<ClassSkillDocument> skills
    ) {}

    private record ClassSkillDocument(
            ObjectId skillId,
            String name,
            List<String> lore,
            String iconMaterial,
            String type,
            String previewSkillUrl,
            SkillProgressionDocument skillProgression
    ) {}

    private record SkillProgressionDocument(
            int unlockLevel,
            int maxLevel,
            Map<String, StatGrowthDocument> stats
    ) {}

    private record StatGrowthDocument(
            Double base,
            Double perLevel,
            Double min,
            Double max
    ) {}

}
