package com.minecraft_wiki.backend.Model.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Strength level of mob")
public enum MobStrength {
    WEAK,
    NORMAL,
    ELITE,
    BOSS
}
