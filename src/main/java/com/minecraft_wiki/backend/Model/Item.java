package com.minecraft_wiki.backend.Model;

import com.minecraft_wiki.backend.Model.enums.ItemRarity;
import com.minecraft_wiki.backend.Model.enums.ItemType;
import com.minecraft_wiki.backend.Model.enums.SpecialCharacteristic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Item {
    private UUID itemId;
    private String name;
    private String description;
    private String imageUrl;

    private ItemType itemType;
    private ItemRarity itemRarity;
    private SpecialCharacteristic specialCharacteristic;

    private ItemStats itemStats;

    private CraftRecipe craftRecipe;
}
