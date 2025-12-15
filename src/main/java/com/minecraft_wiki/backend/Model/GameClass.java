package com.minecraft_wiki.backend.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class GameClass {
    private UUID classId;
    private String name;
    private String description;
    private String imageUrl;

    private List<ClassSkill> skills;
}
