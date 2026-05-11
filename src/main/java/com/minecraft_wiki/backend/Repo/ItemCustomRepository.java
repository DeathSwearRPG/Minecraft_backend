package com.minecraft_wiki.backend.Repo;

import com.minecraft_wiki.backend.Model.Item;
import com.minecraft_wiki.backend.Model.enums.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemCustomRepository {
    Page<Item> getItems(
            String search,
            ItemType type,
            ItemRarity rarity,
            SpecialCharacteristic specialCharacteristic,
            Pageable pageable
    );
}
