package com.minecraft_wiki.backend.DTO;

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

@Schema(description = "detailed information about pet")
public class PetDetailsDto {
    @Schema(description = "unique identifier of pet", example = "69d8be6a57638d712640d21c")
    private String mobId;

    @Schema(description = "name of pet", example = "Wolf")
    private String name;

    @Schema(description = "information about pet", example = "A loyal companion")
    private String description;

    @Schema(description = "Url to get image of pet", example = "https://example.com/wolf.png")
    private String imageUrl;

    @Schema(description = "Location where the pet can be found", example = "Forest biome")
    private String locationInfo;

    @Schema(description = "Stats of the pet (health, damage, armor, growth per level)")
    private MobStats stats;

    @Schema(description = "Additional information about the pet", example = "Can be tamed")
    private List<String> extraInfo;

    @Schema(description = "Strength level of pet", example = "ELITE")
    private MobStrength strength;

    @Builder.Default
    private MobType type = MobType.PET;
}
