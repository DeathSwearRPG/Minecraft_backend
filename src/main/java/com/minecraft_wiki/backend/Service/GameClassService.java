package com.minecraft_wiki.backend.Service;

import com.minecraft_wiki.backend.Model.ClassSkill;
import com.minecraft_wiki.backend.Model.GameClass;
import com.minecraft_wiki.backend.Model.SkillProgression;
import com.minecraft_wiki.backend.Model.StatGrowth;
import com.minecraft_wiki.backend.Model.enums.TriggerType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class GameClassService {

    private final List<GameClass> classes = List.of(
            new GameClass(
                    UUID.randomUUID(),
                    "Берсерк",
                    "Воин, черпающий силу в сражениях. Обладает высоким уроном и регенерацией здоровья в бою.",
                    "https://example.com/classes/berserk.png",
                    List.of(
                            new ClassSkill(
                                    UUID.randomUUID(),
                                    "Жажда крови",
                                    List.of(
                                            "После нанесения урона врагу есть 30% шанс",
                                            "восстановить себе {heal} здоровья.",
                                            "Перезарядка: {cooldown} секунд"
                                    ),
                                    "REDSTONE",
                                    TriggerType.ATTACK,
                                    "https://example.com/skills/berserk_attack.gif",
                                    new SkillProgression(
                                            1,
                                            30,
                                            Map.of(
                                                    "heal", new StatGrowth(0.5, 0.3, null, null),
                                                    "cooldown", new StatGrowth(10, -0.5, 7.0, null)
                                            )
                                    )
                            )
                    )
            ),
            new GameClass(
                    UUID.fromString("00000000-0000-0000-0000-000000000020"),
                    "Ледяной маг",
                    "Маг, способный замораживать врагов и контролировать поле боя.",
                    "https://example.com/classes/ice_mage.png",
                    List.of(
                            new ClassSkill(
                                    UUID.randomUUID(),
                                    "Ледяная стрела",
                                    List.of(
                                            "Вы выпускаете ледяной снаряд,",
                                            "наносящий {damage} урона и",
                                            "замедляющий врага на {duration} секунд.",
                                            "Перезарядка: {cooldown} секунд"
                                    ),
                                    "ICE",
                                    TriggerType.ACTIVE,
                                    "https://example.com/skills/ice_arrow.gif",
                                    new SkillProgression(
                                            1,
                                            30,
                                            Map.of(
                                                    "damage", new StatGrowth(4.0, 0.8, null, null),
                                                    "duration", new StatGrowth(2.0, 0.2, null, 6.0),
                                                    "cooldown", new StatGrowth(8.0, -0.2, 4.0, null)
                                            )
                                    )
                            ),
                            new ClassSkill(
                                    UUID.randomUUID(),
                                    "Ледяная тюрьма",
                                    List.of(
                                            "Вы заключаете врагов в ледяную ловушку,",
                                            "обездвиживая их на {duration} секунд.",
                                            "Перезарядка: {cooldown} секунд"
                                    ),
                                    "PACKED_ICE",
                                    TriggerType.ACTIVE,
                                    "https://example.com/skills/ice_prison.gif",
                                    new SkillProgression(
                                            5,
                                            20,
                                            Map.of(
                                                    "duration", new StatGrowth(3.0, 0.5, null, 10.0),
                                                    "cooldown", new StatGrowth(25.0, -0.7, 12.0, null)
                                            )
                                    )
                            )
                    )
            ),
            new GameClass(
                    UUID.fromString("00000000-0000-0000-0000-000000000030"),
                    "Некромант",
                    "Маг, поклоняющийся Смерти и управляющий силами тьмы.",
                    "https://example.com/classes/necromancer.png",
                    List.of(
                            new ClassSkill(
                                    UUID.randomUUID(),
                                    "Поднятие мёртвых",
                                    List.of(
                                            "Вы воскрешаете падших врагов,",
                                            "призывая {minions} прислужников.",
                                            "Длительность: {duration} секунд."
                                    ),
                                    "WITHER_SKELETON_SKULL",
                                    TriggerType.ACTIVE,
                                    "https://example.com/skills/raise_dead.gif",
                                    new SkillProgression(
                                            1,
                                            15,
                                            Map.of(
                                                    "minions", new StatGrowth(1, 0.3, null, 5.0),
                                                    "duration", new StatGrowth(10.0, 2.0, null, 40.0)
                                            )
                                    )
                            ),
                            new ClassSkill(
                                    UUID.randomUUID(),
                                    "Проклятие разложения",
                                    List.of(
                                            "Вы накладываете проклятие на цель,",
                                            "нанося {damage} урона каждую секунду.",
                                            "Перезарядка: {cooldown} секунд."
                                    ),
                                    "WITHER_ROSE",
                                    TriggerType.ATTACK,
                                    "https://example.com/skills/decay_curse.gif",
                                    new SkillProgression(
                                            3,
                                            30,
                                            Map.of(
                                                    "damage", new StatGrowth(2.0, 0.4, null, null),
                                                    "cooldown", new StatGrowth(12.0, -0.3, 6.0, null)
                                            )
                                    )
                            )
                    )
            )
    );

    public List<GameClass> getClasses() {
        return classes;
    }

    public GameClass getClassById(UUID gameClassId) {
        return classes.stream()
                .filter(gameClass -> gameClass.getClassId().equals(gameClassId))
                .findFirst()
                .orElse(null);
    }
}
