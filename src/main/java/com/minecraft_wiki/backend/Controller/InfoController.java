package com.minecraft_wiki.backend.Controller;


import com.minecraft_wiki.backend.DTO.HomeSlideResponseDto;
import com.minecraft_wiki.backend.Mapper.HomeSlideMapper;
import com.minecraft_wiki.backend.Service.HomeSlideService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/home")
public class InfoController {

    private final HomeSlideService homeSlideService;
    private final HomeSlideMapper homeSlideMapper;

    @GetMapping("/slides")
    public List<HomeSlideResponseDto> getHomeSlides() {
        return homeSlideService.getHomeSlides()
                .stream()
                .map(homeSlideMapper::toResponseDto)
                .toList();
    }

}
