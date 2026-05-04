package com.minecraft_wiki.backend.Mapper;

import com.minecraft_wiki.backend.DTO.GameClassDetailsDto;
import com.minecraft_wiki.backend.DTO.GameClassResponseDto;
import com.minecraft_wiki.backend.Model.GameClass;
import org.springframework.stereotype.Component;

@Component
public class GameClassMapper {
    public GameClassResponseDto toResponseDto(GameClass gameClass) {
        if (gameClass == null) {
            return null;
        }

        return GameClassResponseDto.builder()
                .classId(gameClass.getClassId().toHexString())
                .name(gameClass.getName())
                .imageUrl(gameClass.getImageUrl())
                .build();
    }

    public GameClassDetailsDto toDetailsDto(GameClass gameClass) {
        if (gameClass == null) {
            return null;
        }

        return GameClassDetailsDto.builder()
                .classId(gameClass.getClassId().toHexString())
                .name(gameClass.getName())
                .description(gameClass.getDescription())
                .imageUrl(gameClass.getImageUrl())
                .skills(gameClass.getSkills())
                .build();
    }
}
