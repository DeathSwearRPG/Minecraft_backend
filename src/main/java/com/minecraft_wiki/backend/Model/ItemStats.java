package com.minecraft_wiki.backend.Model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Schema(description = "Statistics of the item")
public class ItemStats {
    @Schema(description = "Gathering speed bonus", example = "5")
    private Integer gatheringSpeed;
    @Schema(description = "Necessary level to use item", example = "10")
    private Integer requiredLevel;
    @Schema(description = "Damage provided by the item", example = "15")
    private Integer damage;
    @Schema(description = "Armor provided by the item", example = "20")
    private Integer armor;
}
