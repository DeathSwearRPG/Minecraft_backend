package com.minecraft_wiki.backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class HomeSlideResponseDto {
    private String slideId;

    private String title;
    private String description;
    private String imageUrl;
}
