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

public class MobResponseDto {
    private String mobId;
    private String name;
    private String imageUrl;
}
