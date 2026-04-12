package com.minecraft_wiki.backend.Controller;

import com.minecraft_wiki.backend.DTO.GameClassDetailsDto;
import com.minecraft_wiki.backend.DTO.GameClassResponseDto;
import com.minecraft_wiki.backend.Mapper.GameClassMapper;
import com.minecraft_wiki.backend.Service.GameClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
@RequiredArgsConstructor

public class GameClassController {

    private final GameClassService gameClassService;
    private final GameClassMapper gameClassMapper;

    @GetMapping
    public List<GameClassResponseDto> getClasses() {
        return gameClassMapper.toResponseDtoList(gameClassService.getGameClasses());
    }

    @GetMapping("/{id}")
    public GameClassDetailsDto getClassById(@PathVariable("id") String gameClassId) {
        return gameClassMapper.toDetailsDto(gameClassService.getGameClassById(gameClassId));
    }

}
