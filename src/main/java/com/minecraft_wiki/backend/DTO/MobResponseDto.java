package com.minecraft_wiki.backend.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Schema(description = "Short information about mob")
public class MobResponseDto {
    @Schema(description = "unique identifier of mob", example = "69d8be6a57475d712640e961")
    private String mobId;
    @Schema(description = "name of mob", example = "Zombie")
    private String name;
    @Schema(description = "Url to get image of mob", example = "https://example.com/zombie.png")
    private String imageUrl;
}
