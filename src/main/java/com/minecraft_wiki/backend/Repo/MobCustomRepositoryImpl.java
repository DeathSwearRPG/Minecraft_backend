package com.minecraft_wiki.backend.Repo;

import com.minecraft_wiki.backend.Model.Mob;
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
public class MobCustomRepositoryImpl implements MobCustomRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public Page<Mob> findMobs(String search, MobStrength strength, MobType type, Pageable pageable) {
        Query query = new Query();

        if (search != null && !search.isBlank()) {
            query.addCriteria(Criteria.where("name").regex(search, "i"));
        }

        if (strength != null) {
            query.addCriteria(Criteria.where("strength").is(strength));
        }

        if (type != null) {
            query.addCriteria(Criteria.where("type").is(type));
        }

        long total = mongoTemplate.count(query, Mob.class);

        query.with(pageable);

        List<Mob> mobs = mongoTemplate.find(query, Mob.class);

        return new PageImpl<>(mobs, pageable, total);
    }
}
