package com.minecraft_wiki.backend.config.migration;

import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

@ChangeUnit(id = "v002-seed-mobs", order = "003", author = "danila")
public class V002SeedBaseItemsChangeUnit {
    private final MongoTemplate mongoTemplate;

    public V002SeedBaseItemsChangeUnit(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Execution
    public void execute() {
        insertIfNotExists(
                "Stone",
                "Базовый каменный материал для крафта",
                "https://example.com/items/stone.png",
                "MATERIAL",
                "COMMON",
                null,
                new ItemStatsDocument(null, null, null, null),
                null
        );

        insertIfNotExists(
                "Stick",
                "Базовый деревянный компонент для крафта",
                "https://example.com/items/stick.png",
                "MATERIAL",
                "COMMON",
                null,
                new ItemStatsDocument(null, null, null, null),
                null
        );

        insertIfNotExists(
                "Iron Ingot",
                "Очищенный железный слиток для создания снаряжения",
                "https://example.com/items/iron_ingot.png",
                "MATERIAL",
                "UNCOMMON",
                null,
                new ItemStatsDocument(null, null, null, null),
                null
        );

        insertIfNotExists(
                "Leather",
                "Кусок обработанной кожи для брони и других предметов",
                "https://example.com/items/leather.png",
                "MATERIAL",
                "COMMON",
                null,
                new ItemStatsDocument(null, null, null, null),
                null
        );
    }

    @RollbackExecution
    public void rollback() {
        mongoTemplate.remove(new Query(), "items");
    }

    private void insertIfNotExists(
            String name,
            String description,
            String imageUrl,
            String itemType,
            String itemRarity,
            String specialCharacteristic,
            ItemStatsDocument itemStats,
            CraftRecipeDocument craftRecipe
    ) {
        Query query = Query.query(Criteria.where("name").is(name));

        if (mongoTemplate.exists(query, "items")) {
            return;
        }

        ItemDocument item = new ItemDocument(
                name,
                description,
                imageUrl,
                itemType,
                itemRarity,
                specialCharacteristic,
                itemStats,
                craftRecipe
        );

        mongoTemplate.insert(item, "items");
    }

    private record ItemDocument(
            String name,
            String description,
            String imageUrl,
            String itemType,
            String itemRarity,
            String specialCharacteristic,
            ItemStatsDocument itemStats,
            CraftRecipeDocument craftRecipe
    ) {}

    private record ItemStatsDocument(
            Integer gatheringSpeed,
            Integer requiredLevel,
            Integer damage,
            Integer armor
    ) {}

    private record CraftRecipeDocument(
            java.util.List<CraftSlotDocument> slots
    ) {}

    private record CraftSlotDocument(
            org.bson.types.ObjectId itemId
    ) {}
}
