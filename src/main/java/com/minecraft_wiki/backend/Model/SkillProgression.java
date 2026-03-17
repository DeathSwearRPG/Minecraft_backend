package com.minecraft_wiki.backend.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class SkillProgression {
    private int unlockLevel;
    private int maxLevel;

    private Map<String, StatGrowth> stats;
}
