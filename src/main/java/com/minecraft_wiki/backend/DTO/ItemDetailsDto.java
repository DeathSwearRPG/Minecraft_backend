package com.minecraft_wiki.backend.DTO;

import com.minecraft_wiki.backend.Model.CraftRecipe;
import com.minecraft_wiki.backend.Model.ItemStats;
import com.minecraft_wiki.backend.Model.enums.ItemRarity;
import com.minecraft_wiki.backend.Model.enums.ItemType;
import com.minecraft_wiki.backend.Model.enums.SpecialCharacteristic;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Schema(description = "Detailed information about item")
public class ItemDetailsDto {
    @Schema(description = "Unique identifier of item", example = "69d8be6a57475d712640e520")
    private String itemId;

    @Schema(description = "Name of item", example = "Stick")
    private String name;

    @Schema(description = "Information about item", example = "Базовый деревянный компонент для крафта")
    private String description;

    @Schema(description = "Url to get image of item", example = "https://example.com/items/stick.png")
    private String imageUrl;

    @Schema(description = "Type of the item", example = "MATERIAL")
    private ItemType itemType;

    @Schema(description = "Rarity of the item", example = "COMMON")
    private ItemRarity itemRarity;

    @Schema(description = "Special characteristic of the item", example = "SCALABLE")
    private SpecialCharacteristic specialCharacteristic;

    @Schema(description = "Item stats (damage, armor, required level, gathering speed)")
    private ItemStats itemStats;

    @Schema(description = "Crafting recipe required to create the item")
    private CraftRecipe craftRecipe;
}
