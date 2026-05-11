package com.minecraft_wiki.backend.Repo;

import com.minecraft_wiki.backend.Model.Pet;
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

public class PetCustomRepositoryImpl implements PetCustomRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public Page<Pet> findPets(String search, MobStrength strength, Pageable pageable) {
        Query query = new Query();

        query.addCriteria(Criteria.where("type").is(MobType.PET));

        if (search != null && !search.isBlank()) {
            query.addCriteria(Criteria.where("name").regex(search, "i"));
        }

        if (strength != null) {
            query.addCriteria(Criteria.where("strength").is(strength));
        }

        long total = mongoTemplate.count(query, Pet.class);

        query.with(pageable);

        List<Pet> pets = mongoTemplate.find(query, Pet.class);

        return new PageImpl<>(pets, pageable, total);
    }
}
