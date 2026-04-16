package com.minecraft_wiki.backend.Service;

import com.minecraft_wiki.backend.Model.GameClass;
import com.minecraft_wiki.backend.Repo.GameClassMongoRepository;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GameClassService {

    private final GameClassMongoRepository gameClassMongoRepository;

    public List<GameClass> getGameClasses() {
        return gameClassMongoRepository.findAll();
    }

    public GameClass getGameClassById(String classId) {
        try {
            ObjectId objectId = new ObjectId(classId);

            return gameClassMongoRepository.findById(objectId)
                    .orElseThrow(() -> new RuntimeException("boss doesn't exist " + classId));

        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid boss id format: " + classId);
        }
    }
}
