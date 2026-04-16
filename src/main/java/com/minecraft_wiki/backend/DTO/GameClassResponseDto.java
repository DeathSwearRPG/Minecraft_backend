package com.minecraft_wiki.backend.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Schema(description = "Short information about game class")
public class GameClassResponseDto {
    @Schema(description = "unique identifier of game class", example = "69dbe5c29ab35d5c5d645bee")
    private String classId;

    @Schema(description = "name of game class", example = "Берсерк")
    private String name;

    @Schema(description = "URL of the class image", example = "https://example.com/classes/berserk.png")
    private String imageUrl;
}
