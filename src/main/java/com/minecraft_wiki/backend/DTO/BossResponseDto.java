package com.minecraft_wiki.backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class BossResponseDto {
    private UUID mobId;
    private String name;
    private String imageUrl;
}
