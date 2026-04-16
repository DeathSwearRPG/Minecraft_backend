package com.minecraft_wiki.backend.DTO;

import com.minecraft_wiki.backend.Model.DropItem;
import com.minecraft_wiki.backend.Model.MobStats;
import com.minecraft_wiki.backend.Model.enums.MobStrength;
import com.minecraft_wiki.backend.Model.enums.MobType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Schema(description = "detailed information about boss")
public class BossDetailsDto {
    @Schema(description = "unique identifier of boss", example = "69d8be6a57475d712640e51e")
    private String mobId;

    @Schema(description = "name of boss", example = "Ender dragon")
    private String name;

    @Schema(description = "information about boss", example = "Главный босс измерения Края")
    private String description;

    @Schema(description = "Url to get image of boss", example = "https://example.com/dragon.png")
    private String imageUrl;

    @Schema(description = "Location where the boss can be found", example = "Измерение Края")
    private String locationInfo;

    @Schema(description = "Stats of the boss (health, damage, armor, growth per level)")
    private MobStats stats;

    @Schema(description = "List of items dropped by the boss")
    private List<DropItem> drops;

    @Schema(description = "Additional information about the boss", example = "Летает")
    private List<String> extraInfo;

    @Schema(description = "Strength level of the boss", example = "BOSS")
    private MobStrength strength;

    @Schema(description = "Type of the boss", example = "MONSTER")
    private MobType type;
}
