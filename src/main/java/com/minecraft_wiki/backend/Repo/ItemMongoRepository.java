package com.minecraft_wiki.backend.Repo;

import com.minecraft_wiki.backend.Model.Item;
import com.minecraft_wiki.backend.Model.enums.ItemRarity;
import com.minecraft_wiki.backend.Model.enums.ItemType;
import com.minecraft_wiki.backend.Model.enums.SpecialCharacteristic;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ItemMongoRepository extends MongoRepository<Item, ObjectId> {
    List<Item> findByItemRarity(ItemRarity rarity);
    List<Item> findByItemType(ItemType type);
    List<Item> findBySpecialCharacteristic(SpecialCharacteristic characteristic);
    List<Item> findByItemRarityAndItemTypeAndSpecialCharacteristic(ItemRarity rarity, ItemType type, SpecialCharacteristic characteristic);
}
