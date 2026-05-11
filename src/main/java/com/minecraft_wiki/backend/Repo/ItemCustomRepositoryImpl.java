package com.minecraft_wiki.backend.Repo;

import com.minecraft_wiki.backend.Model.Item;
import com.minecraft_wiki.backend.Model.enums.ItemRarity;
import com.minecraft_wiki.backend.Model.enums.ItemType;
import com.minecraft_wiki.backend.Model.enums.SpecialCharacteristic;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor

public class ItemCustomRepositoryImpl implements ItemCustomRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public Page<Item> getItems(String search, ItemType type, ItemRarity rarity, SpecialCharacteristic specialCharacteristic, Pageable pageable) {
        Query query = new Query();

        if (search != null && !search.isBlank()) {
            query.addCriteria(Criteria.where("name").regex(search, "i"));
        }

        if (type != null) {
            query.addCriteria(Criteria.where("itemType").is(type));
        }

        if (rarity != null) {
            query.addCriteria(Criteria.where("itemRarity").is(rarity));
        }

        if (specialCharacteristic != null) {
            query.addCriteria(Criteria.where("specialCharacteristic").is(specialCharacteristic));
        }

        long total = mongoTemplate.count(query, Item.class);

        query.with(pageable);

        List<Item> items = mongoTemplate.find(query, Item.class);

        return new PageImpl<>(items, pageable, total);
    }
}
