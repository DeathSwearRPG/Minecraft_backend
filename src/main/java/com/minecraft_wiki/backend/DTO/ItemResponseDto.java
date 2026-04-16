package com.minecraft_wiki.backend.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Schema(description = "Short information about item")
public class ItemResponseDto {
    @Schema(description = "unique identifier of item", example = "69d8be6a57475d712640e520")
    private String itemId;

    @Schema(description = "name of item", example = "Stick")
    private String name;

    @Schema(description = "Url to get image of item", example = "https://example.com/items/stick.png")
    private String imageUrl;
}
