package com.minecraft_wiki.backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class BossResponseDto {
    private String mobId;
    private String name;
    private String imageUrl;
}
