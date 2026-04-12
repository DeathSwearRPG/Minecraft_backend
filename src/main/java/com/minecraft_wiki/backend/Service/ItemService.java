package com.minecraft_wiki.backend.Service;

import com.minecraft_wiki.backend.Model.*;
import com.minecraft_wiki.backend.Model.enums.ItemRarity;
import com.minecraft_wiki.backend.Model.enums.ItemType;
import com.minecraft_wiki.backend.Model.enums.SpecialCharacteristic;
import com.minecraft_wiki.backend.Repo.ItemMongoRepository;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ItemService {

    private final ItemMongoRepository itemMongoRepository;

    public List<Item> getItems(ItemRarity rarity, ItemType type, SpecialCharacteristic characteristic) {
        if (rarity != null && type != null && characteristic != null) {
            return itemMongoRepository.findByItemRarityAndItemTypeAndSpecialCharacteristic(rarity, type, characteristic);
        }

        if (rarity != null) {
            return itemMongoRepository.findByItemRarity(rarity);
        }

        if (type != null) {
            return itemMongoRepository.findByItemType(type);
        }

        if (characteristic != null) {
            return itemMongoRepository.findBySpecialCharacteristic(characteristic);
        }

        return itemMongoRepository.findAll();
    }

    public Item getItemById(String itemId) {
        try {
            ObjectId objectId = new ObjectId(itemId);

            return itemMongoRepository.findById(objectId)
                    .orElseThrow(() -> new RuntimeException("item doesn't exist " + itemId));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid item id format: " + itemId);
        }
    }
}
