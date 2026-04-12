package com.minecraft_wiki.backend.Model;

import com.minecraft_wiki.backend.Model.enums.ItemRarity;
import com.minecraft_wiki.backend.Model.enums.ItemType;
import com.minecraft_wiki.backend.Model.enums.SpecialCharacteristic;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "items")

public class Item {
    @Id
    private ObjectId itemId;
    private String name;
    private String description;
    private String imageUrl;

    private ItemType itemType;
    private ItemRarity itemRarity;
    private SpecialCharacteristic specialCharacteristic;

    private ItemStats itemStats;

    private CraftRecipe craftRecipe;
}
