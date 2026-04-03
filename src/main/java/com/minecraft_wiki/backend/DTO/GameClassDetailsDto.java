package com.minecraft_wiki.backend.DTO;

import com.minecraft_wiki.backend.Model.ClassSkill;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class GameClassDetailsDto {
    private UUID classId;
    private String name;
    private String description;
    private String imageUrl;

    private List<ClassSkill> skills;
}
