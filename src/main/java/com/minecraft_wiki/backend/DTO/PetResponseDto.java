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

@Schema(description = "Short information about pet")
public class PetResponseDto {
    @Schema(description = "unique identifier of pet", example = "69d8be6a57638d712640d21c")
    private String mobId;
    @Schema(description = "name of pet", example = "Wolf")
    private String name;
    @Schema(description = "Url to get image of pet", example = "https://example.com/wolf.png")
    private String imageUrl;
}
