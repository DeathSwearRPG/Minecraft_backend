package com.minecraft_wiki.backend.Service;

import com.minecraft_wiki.backend.Model.GameClass;
import com.minecraft_wiki.backend.Repo.GameClassMongoRepository;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GameClassService {

    private final GameClassMongoRepository gameClassMongoRepository;

    public Page<GameClass> getGameClasses(Pageable pageable) {
        return gameClassMongoRepository.findAll(pageable);
    }

    public GameClass getGameClassById(String classId) {
        try {
            ObjectId objectId = new ObjectId(classId);

            return gameClassMongoRepository.findById(objectId)
                    .orElseThrow(() -> new RuntimeException("game class doesn't exist " + classId));

        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid game class id format: " + classId);
        }
    }
}
