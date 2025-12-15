package com.minecraft_wiki.backend.Model;

import com.minecraft_wiki.backend.Model.enums.TriggerType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ClassSkill {
    private UUID skillId;
    private String name;
    private List<String> lore;
    private String iconMaterial;
    private TriggerType type;

    private String previewSkillUrl;

    private SkillProgression skillProgression;
}
