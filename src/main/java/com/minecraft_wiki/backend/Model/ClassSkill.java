package com.minecraft_wiki.backend.Model;

import com.minecraft_wiki.backend.Model.enums.TriggerType;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ClassSkill {
    private UUID skillId;
    private String name;
    private List<String> lore;
    private String iconMaterial;
    private TriggerType type;

    private String previewSkillUrl;

    private SkillProgression skillProgression;
}
