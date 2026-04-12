package com.minecraft_wiki.backend.DTO;

import com.minecraft_wiki.backend.Model.CraftRecipe;
import com.minecraft_wiki.backend.Model.ItemStats;
import com.minecraft_wiki.backend.Model.enums.ItemRarity;
import com.minecraft_wiki.backend.Model.enums.ItemType;
import com.minecraft_wiki.backend.Model.enums.SpecialCharacteristic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ItemDetailsDto {
    private String itemId;
    private String name;
    private String description;
    private String imageUrl;

    private ItemType itemType;
    private ItemRarity itemRarity;
    private SpecialCharacteristic specialCharacteristic;

    private ItemStats itemStats;

    private CraftRecipe craftRecipe;
}
