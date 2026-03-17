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
            Item.builder()
                    .itemId(UUID.randomUUID())
                    .name("Axe")
                    .description("pick axe")
                    .imageUrl("https:/erhgrekwefw")
                    .itemType(ItemType.TOOL)
                    .itemRarity(ItemRarity.LEGENDARY)
                    .specialCharacteristic(SpecialCharacteristic.SCALABLE)
                    .itemStats(ItemStats.builder()
                            .gatheringSpeed(15)
                            .damage(120)
                            .armor(20)
                            .build())
                    .craftRecipe(new CraftRecipe(List.of(
                            new CraftSlot(MockItems.STONE),
                            new CraftSlot(MockItems.STONE),
                            new CraftSlot(MockItems.STONE),
                            new CraftSlot(null),
                            new CraftSlot(MockItems.STICK),
                            new CraftSlot(null),
                            new CraftSlot(null),
                            new CraftSlot(MockItems.STICK),
                            new CraftSlot(null)
                    )))
                    .build(),

            Item.builder()
                    .itemId(UUID.randomUUID())
                    .name("Helmet")
                    .description("wear")
                    .imageUrl("https:/erhgrekwefw")
                    .itemType(ItemType.ARMOR)
                    .itemRarity(ItemRarity.MYTHIC)
                    .specialCharacteristic(SpecialCharacteristic.SCALABLE)
                    .itemStats(ItemStats.builder()
                            .gatheringSpeed(15)
                            .requiredLevel(null)
                            .damage(120)
                            .armor(20)
                            .build())
                    .craftRecipe(new CraftRecipe(List.of(
                            new CraftSlot(MockItems.STONE),
                            new CraftSlot(MockItems.STONE),
                            new CraftSlot(MockItems.STONE),
                            new CraftSlot(null),
                            new CraftSlot(MockItems.STICK),
                            new CraftSlot(null),
                            new CraftSlot(null),
                            new CraftSlot(MockItems.STICK),
                            new CraftSlot(null)
                    )))
                    .build(),

            Item.builder()
                    .itemId(UUID.randomUUID())
                    .name("Sword")
                    .description("pick axe")
                    .imageUrl("https:/erhgrekwefw")
                    .itemType(ItemType.WEAPON)
                    .itemRarity(ItemRarity.EPIC)
                    .itemStats(ItemStats.builder()
                            .gatheringSpeed(15)
                            .damage(120)
                            .armor(20)
                            .build())
                    .craftRecipe(new CraftRecipe(List.of(
                            new CraftSlot(MockItems.STONE),
                            new CraftSlot(MockItems.STONE),
                            new CraftSlot(MockItems.STONE),
                            new CraftSlot(null),
                            new CraftSlot(MockItems.STICK),
                            new CraftSlot(null),
                            new CraftSlot(null),
                            new CraftSlot(MockItems.STICK),
                            new CraftSlot(null)
                    )))
                    .build()
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
