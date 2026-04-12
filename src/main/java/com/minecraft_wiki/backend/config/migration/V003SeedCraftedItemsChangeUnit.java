package com.minecraft_wiki.backend.config.migration;

import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

@ChangeUnit(id = "v003-seed-crafted-items", order = "003", author = "danila")
public class V003SeedCraftedItemsChangeUnit {
    private final MongoTemplate mongoTemplate;

    public V003SeedCraftedItemsChangeUnit(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Execution
    public void execute() {
        ObjectId ironIngotId = getItemIdByName("Iron Ingot");
        ObjectId stickId = getItemIdByName("Stick");
        ObjectId leatherId = getItemIdByName("Leather");

        insertIfNotExists(
                "Iron Sword",
                "Железный меч для ближнего боя",
                "https://example.com/items/iron_sword.png",
                "WEAPON",
                "UNCOMMON",
                "SCALABLE",
                new ItemStatsDocument(0, 8, 6, 0),
                new CraftRecipeDocument(List.of(
                        new CraftSlotDocument(ironIngotId),
                        new CraftSlotDocument(null),
                        new CraftSlotDocument(null),
                        new CraftSlotDocument(ironIngotId),
                        new CraftSlotDocument(null),
                        new CraftSlotDocument(null),
                        new CraftSlotDocument(stickId),
                        new CraftSlotDocument(null),
                        new CraftSlotDocument(null)
                ))
        );

        insertIfNotExists(
                "Leather Cap",
                "Кожаный шлем начального уровня",
                "https://example.com/items/leather_cap.png",
                "ARMOR",
                "COMMON",
                null,
                new ItemStatsDocument(0, 3, 0, 1),
                new CraftRecipeDocument(List.of(
                        new CraftSlotDocument(leatherId),
                        new CraftSlotDocument(leatherId),
                        new CraftSlotDocument(leatherId),
                        new CraftSlotDocument(leatherId),
                        new CraftSlotDocument(null),
                        new CraftSlotDocument(leatherId),
                        new CraftSlotDocument(null),
                        new CraftSlotDocument(null),
                        new CraftSlotDocument(null)
                ))
        );
    }

    @RollbackExecution
    public void rollback() {
        mongoTemplate.remove(Query.query(Criteria.where("name").is("Iron Sword")), "items");
        mongoTemplate.remove(Query.query(Criteria.where("name").is("Leather Cap")), "items");
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

    private ObjectId getItemIdByName(String name) {
        Query query = Query.query(Criteria.where("name").is(name));
        ItemLookupDocument item = mongoTemplate.findOne(query, ItemLookupDocument.class, "items");

        if (item == null || item.id() == null) {
            throw new RuntimeException("Base item not found: " + name);
        }

        return item.id();
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
            List<CraftSlotDocument> slots
    ) {}

    private record CraftSlotDocument(
            ObjectId itemId
    ) {}

    private record ItemLookupDocument(
            @Id ObjectId id,
            String name
    ) {}
}
