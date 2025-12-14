package com.minecraft_wiki.backend.Service;

import com.minecraft_wiki.backend.Model.*;
import com.minecraft_wiki.backend.Model.enums.ItemRarity;
import com.minecraft_wiki.backend.Model.enums.ItemType;
import com.minecraft_wiki.backend.Model.enums.SpecialCharacteristic;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ItemService {

    private final List<Item> items = List.of(
            new Item(UUID.randomUUID(),
                    "Axe",
                    "pick axe",
                    "https:/erhgrekwefw",
                    ItemType.TOOL,
                    ItemRarity.LEGENDARY,
                    SpecialCharacteristic.SCALABLE,
                    new ItemStats(
                            15,
                            null,
                            120,
                            20
                    ),
                    new CraftRecipe(List.of(
                            new CraftSlot(MockItems.STONE),
                            new CraftSlot(MockItems.STONE),
                            new CraftSlot(MockItems.STONE),
                            new CraftSlot(null),
                            new CraftSlot(MockItems.STICK),
                            new CraftSlot(null),
                            new CraftSlot(null),
                            new CraftSlot(MockItems.STICK),
                            new CraftSlot(null)
                    ))
            ),
            new Item(UUID.randomUUID(),
                    "Helmet",
                    "wear",
                    "https:/erhgrekwefw",
                    ItemType.ARMOR,
                    ItemRarity.MYTHIC,
                    SpecialCharacteristic.SCALABLE,
                    new ItemStats(
                            15,
                            null,
                            120,
                            20
                    ),
                    new CraftRecipe(List.of(
                            new CraftSlot(MockItems.STONE),
                            new CraftSlot(MockItems.STONE),
                            new CraftSlot(MockItems.STONE),
                            new CraftSlot(null),
                            new CraftSlot(MockItems.STICK),
                            new CraftSlot(null),
                            new CraftSlot(null),
                            new CraftSlot(MockItems.STICK),
                            new CraftSlot(null)
                    ))
            ),
            new Item(UUID.randomUUID(),
                    "Sword",
                    "pick axe",
                    "https:/erhgrekwefw",
                    ItemType.WEAPON,
                    ItemRarity.EPIC,
                    null,
                    new ItemStats(
                            15,
                            null,
                            120,
                            20
                    ),
                    new CraftRecipe(List.of(
                            new CraftSlot(MockItems.STONE),
                            new CraftSlot(MockItems.STONE),
                            new CraftSlot(MockItems.STONE),
                            new CraftSlot(null),
                            new CraftSlot(MockItems.STICK),
                            new CraftSlot(null),
                            new CraftSlot(null),
                            new CraftSlot(MockItems.STICK),
                            new CraftSlot(null)
                    ))
            )
    );

    public List<Item> getItems(ItemType itemType, ItemRarity itemRarity, SpecialCharacteristic specialCharacteristic) {
        return items
                .stream()
                .filter(item -> itemType == null || item.getItemType() == itemType)
                .filter(item -> itemRarity == null || item.getItemRarity() == itemRarity)
                .filter(item -> specialCharacteristic == null || item.getSpecialCharacteristic() == specialCharacteristic)
                .toList();
    }

    public Item getItemById(UUID itemId) {
        return items.stream()
                .filter(item -> item.getItemId().equals(itemId))
                .findFirst()
                .orElse(null);
    }
}
