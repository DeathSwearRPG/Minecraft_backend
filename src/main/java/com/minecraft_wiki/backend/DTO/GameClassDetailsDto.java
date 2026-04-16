package com.minecraft_wiki.backend.DTO;

import com.minecraft_wiki.backend.Model.ClassSkill;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Schema(description = "Detailed information about game class")
public class GameClassDetailsDto {
    @Schema(description = "unique identifier of game class", example = "69dbe5c29ab35d5c5d645bee")
    private String classId;

    @Schema(description = "name of game class", example = "Берсерк")
    private String name;

    @Schema(description = "Information about game class")
    private String description;

    @Schema(description = "URL of the class image", example = "https://example.com/classes/berserk.png")
    private String imageUrl;

    @Schema(description = "List of special class skills")
    private List<ClassSkill> skills;
}
