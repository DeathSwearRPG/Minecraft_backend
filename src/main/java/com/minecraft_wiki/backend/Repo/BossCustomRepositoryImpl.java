package com.minecraft_wiki.backend.Repo;

import com.minecraft_wiki.backend.Model.Boss;
import com.minecraft_wiki.backend.Model.enums.MobStrength;
import com.minecraft_wiki.backend.Model.enums.MobType;
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

public class BossCustomRepositoryImpl implements BossCustomRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public Page<Boss> findBosses(String search, MobType type, Pageable pageable) {
        Query query = new Query();

        query.addCriteria(Criteria.where("strength").is(MobStrength.BOSS));

        if (search != null && !search.isBlank()) {
            query.addCriteria(Criteria.where("name").regex(search, "i"));
        }

        if (type != null) {
            query.addCriteria(Criteria.where("type").is(type));
        }

        long total = mongoTemplate.count(query, Boss.class);

        query.with(pageable);

        List<Boss> bosses = mongoTemplate.find(query, Boss.class);

        return new PageImpl<>(bosses, pageable, total);
    }
}
