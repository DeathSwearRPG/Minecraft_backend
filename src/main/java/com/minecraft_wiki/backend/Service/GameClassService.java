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
            GameClass.builder()
                    .classId(UUID.randomUUID())
                    .name("Берсерк")
                    .description("Воин, черпающий силу в сражениях. Обладает высоким уроном и регенерацией здоровья в бою.")
                    .imageUrl("https://example.com/classes/berserk.png")
                    .skills(List.of(
                            ClassSkill.builder()
                                    .skillId(UUID.randomUUID())
                                    .name("Жажда крови")
                                    .lore(List.of(
                                            "После нанесения урона врагу есть 30% шанс",
                                            "восстановить себе {heal} здоровья.",
                                            "Перезарядка: {cooldown} секунд"
                                    ))
                                    .iconMaterial("REDSTONE")
                                    .type(TriggerType.ATTACK)
                                    .previewSkillUrl("https://example.com/skills/berserk_attack.gif")
                                    .skillProgression(new SkillProgression(
                                            1,
                                            30,
                                            Map.of(
                                                    "heal", new StatGrowth(0.5, 0.3, null, null),
                                                    "cooldown", new StatGrowth(10, -0.5, 7.0, null)
                                            )
                                    ))
                                    .build()
                    ))
                    .build(),

            GameClass.builder()
                    .classId(UUID.fromString("00000000-0000-0000-0000-000000000020"))
                    .name("Ледяной маг")
                    .description("Маг, способный замораживать врагов и контролировать поле боя.")
                    .imageUrl("https://example.com/classes/ice_mage.png")
                    .skills(List.of(
                            ClassSkill.builder()
                                    .skillId(UUID.randomUUID())
                                    .name("Ледяная стрела")
                                    .lore(List.of(
                                            "Вы выпускаете ледяной снаряд,",
                                            "наносящий {damage} урона и",
                                            "замедляющий врага на {duration} секунд.",
                                            "Перезарядка: {cooldown} секунд"
                                    ))
                                    .iconMaterial("ICE")
                                    .type(TriggerType.ACTIVE)
                                    .previewSkillUrl("https://example.com/skills/ice_arrow.gif")
                                    .skillProgression(new SkillProgression(
                                            1,
                                            30,
                                            Map.of(
                                                    "damage", new StatGrowth(4.0, 0.8, null, null),
                                                    "duration", new StatGrowth(2.0, 0.2, null, 6.0),
                                                    "cooldown", new StatGrowth(8.0, -0.2, 4.0, null)
                                            )
                                    ))
                                    .build(),

                            ClassSkill.builder()
                                    .skillId(UUID.randomUUID())
                                    .name("Ледяная тюрьма")
                                    .lore(List.of(
                                            "Вы заключаете врагов в ледяную ловушку,",
                                            "обездвиживая их на {duration} секунд.",
                                            "Перезарядка: {cooldown} секунд"
                                    ))
                                    .iconMaterial("PACKED_ICE")
                                    .type(TriggerType.ACTIVE)
                                    .previewSkillUrl("https://example.com/skills/ice_prison.gif")
                                    .skillProgression(new SkillProgression(
                                            5,
                                            20,
                                            Map.of(
                                                    "duration", new StatGrowth(3.0, 0.5, null, 10.0),
                                                    "cooldown", new StatGrowth(25.0, -0.7, 12.0, null)
                                            )
                                    ))
                                    .build()
                    ))
                    .build(),

            GameClass.builder()
                    .classId(UUID.fromString("00000000-0000-0000-0000-000000000030"))
                    .name("Некромант")
                    .description("Маг, поклоняющийся Смерти и управляющий силами тьмы.")
                    .imageUrl("https://example.com/classes/necromancer.png")
                    .skills(List.of(
                            ClassSkill.builder()
                                    .skillId(UUID.randomUUID())
                                    .name("Поднятие мёртвых")
                                    .lore(List.of(
                                            "Вы воскрешаете падших врагов,",
                                            "призывая {minions} прислужников.",
                                            "Длительность: {duration} секунд."
                                    ))
                                    .iconMaterial("WITHER_SKELETON_SKULL")
                                    .type(TriggerType.ACTIVE)
                                    .previewSkillUrl("https://example.com/skills/raise_dead.gif")
                                    .skillProgression(new SkillProgression(
                                            1,
                                            15,
                                            Map.of(
                                                    "minions", new StatGrowth(1, 0.3, null, 5.0),
                                                    "duration", new StatGrowth(10.0, 2.0, null, 40.0)
                                            )
                                    ))
                                    .build(),

                            ClassSkill.builder()
                                    .skillId(UUID.randomUUID())
                                    .name("Проклятие разложения")
                                    .lore(List.of(
                                            "Вы накладываете проклятие на цель,",
                                            "нанося {damage} урона каждую секунду.",
                                            "Перезарядка: {cooldown} секунд."
                                    ))
                                    .iconMaterial("WITHER_ROSE")
                                    .type(TriggerType.ATTACK)
                                    .previewSkillUrl("https://example.com/skills/decay_curse.gif")
                                    .skillProgression(new SkillProgression(
                                            3,
                                            30,
                                            Map.of(
                                                    "damage", new StatGrowth(2.0, 0.4, null, null),
                                                    "cooldown", new StatGrowth(12.0, -0.3, 6.0, null)
                                            )
                                    ))
                                    .build()
                    ))
                    .build()
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
