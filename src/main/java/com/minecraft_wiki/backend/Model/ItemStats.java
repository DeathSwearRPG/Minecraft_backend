package com.minecraft_wiki.backend.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ItemStats {
    private Integer gatheringSpeed;
    private Integer requiredLevel;

    private Integer damage;

    private Integer armor;
}
