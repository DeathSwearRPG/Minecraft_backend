package com.minecraft_wiki.backend.Model;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class GameClass {
    private UUID classId;
    private String name;
    private String description;
    private String imageUrl;

    private List<ClassSkill> skills;
}
