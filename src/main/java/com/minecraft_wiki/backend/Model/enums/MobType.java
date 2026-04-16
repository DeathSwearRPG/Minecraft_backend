package com.minecraft_wiki.backend.Model.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Type of mob")
public enum MobType {
    MONSTER,
    RESISTANCE,
    PET,
    SPIRIT,
    MINION
}
