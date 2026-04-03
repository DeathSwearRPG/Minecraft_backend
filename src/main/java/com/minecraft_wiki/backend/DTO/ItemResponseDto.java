package com.minecraft_wiki.backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ItemResponseDto {
    private UUID itemId;
    private String name;
    private String imageUrl;
}
