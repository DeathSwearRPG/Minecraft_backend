package com.minecraft_wiki.backend.Model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "gameClasses")

public class GameClass {
    @Id
    private ObjectId classId;
    private String name;
    private String description;
    private String imageUrl;

    private List<ClassSkill> skills;
}
