package com.minecraft_wiki.backend.Mapper;

import com.minecraft_wiki.backend.DTO.HomeSlideResponseDto;
import com.minecraft_wiki.backend.Model.HomeSlide;
import org.springframework.stereotype.Component;

@Component
public class HomeSlideMapper {
    public HomeSlideResponseDto toResponseDto(HomeSlide homeSlide) {
        if (homeSlide == null) {
            return null;
        }

        return HomeSlideResponseDto.builder()
                .slideId(homeSlide.getSlideId().toHexString())
                .title(homeSlide.getTitle())
                .description(homeSlide.getDescription())
                .imageUrl(homeSlide.getImageUrl())
                .build();
    }
}
