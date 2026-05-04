package com.minecraft_wiki.backend.Repo;

import com.minecraft_wiki.backend.Model.Item;
import com.minecraft_wiki.backend.Model.enums.ItemRarity;
import com.minecraft_wiki.backend.Model.enums.ItemType;
import com.minecraft_wiki.backend.Model.enums.SpecialCharacteristic;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemMongoRepository extends MongoRepository<Item, ObjectId> {
    Page<Item> findByItemRarity(ItemRarity rarity, Pageable pageable);
    Page<Item> findByItemType(ItemType type, Pageable pageable);
    Page<Item> findBySpecialCharacteristic(SpecialCharacteristic characteristic, Pageable pageable);
    Page<Item> findByItemRarityAndItemTypeAndSpecialCharacteristic(ItemRarity rarity, ItemType type, SpecialCharacteristic characteristic, Pageable pageable);
}
