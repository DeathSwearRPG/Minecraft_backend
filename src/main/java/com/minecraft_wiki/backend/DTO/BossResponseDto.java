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

@Schema(description = "Short information about boss")
public class BossResponseDto {
    @Schema(description = "unique identifier of boss", example = "69d8be6a57475d712640e51e")
    private String mobId;
    @Schema(description = "name of boss", example = "Ender dragon")
    private String name;
    @Schema(description = "Url to get image of boss", example = "https://example.com/dragon.png")
    private String imageUrl;
}
