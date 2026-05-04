package com.minecraft_wiki.backend.config.migration;

import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

@ChangeUnit(id = "v005-seed-home-slides", order = "005", author = "danila")
public class V005SeedHomeSlidesChangeUnit {

    private final MongoTemplate mongoTemplate;

    public V005SeedHomeSlidesChangeUnit(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Execution
    public void execute() {
        insertIfNotExists(
                "Добро пожаловать",
                "Добро пожаловать на сервер DeathSwear. Здесь можно узнать основную информацию о мире, классах, боссах и игровых механиках.",
                "https://example.com/home/welcome.png",
                1
        );

        insertIfNotExists(
                "Об игре",
                "DeathSwear — это Minecraft-сервер с собственными игровыми механиками, системой классов, прокачкой персонажа и уникальными локациями.",
                "https://example.com/home/about.png",
                2
        );

        insertIfNotExists(
                "Классы",
                "Выбирай подходящий класс персонажа, изучай его навыки и развивай стиль игры под свои предпочтения.",
                "https://example.com/home/classes.png",
                3
        );

        insertIfNotExists(
                "Боссы",
                "Сражайся с опасными боссами, изучай их особенности, характеристики и возможные награды за победу.",
                "https://example.com/home/bosses.png",
                4
        );

        insertIfNotExists(
                "Локации",
                "Исследуй игровые зоны, находи редкие ресурсы, опасных существ и уникальные места мира DeathSwear.",
                "https://example.com/home/locations.png",
                5
        );
    }

    @RollbackExecution
    public void rollback() {
        mongoTemplate.remove(new Query(), "homeSlides");
    }

    private void insertIfNotExists(
            String title,
            String description,
            String imageUrl,
            int displayOrder
    ) {
        Query query = Query.query(Criteria.where("title").is(title));

        if (mongoTemplate.exists(query, "homeSlides")) {
            return;
        }

        HomeSlideDocument slide = new HomeSlideDocument(
                title,
                description,
                imageUrl,
                displayOrder
        );

        mongoTemplate.insert(slide, "homeSlides");
    }

    private record HomeSlideDocument(
            String title,
            String description,
            String imageUrl,
            int displayOrder
    ) {}
}
