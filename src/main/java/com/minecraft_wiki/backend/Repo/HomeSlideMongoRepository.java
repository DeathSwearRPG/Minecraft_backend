package com.minecraft_wiki.backend.Repo;

import com.minecraft_wiki.backend.Model.HomeSlide;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface HomeSlideMongoRepository extends MongoRepository<HomeSlide, ObjectId> {

    List<HomeSlide> findAllByOrderByDisplayOrderAsc();

}
