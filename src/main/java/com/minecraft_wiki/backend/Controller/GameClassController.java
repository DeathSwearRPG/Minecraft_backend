package com.minecraft_wiki.backend.Controller;

import com.minecraft_wiki.backend.DTO.GameClassDetailsDto;
import com.minecraft_wiki.backend.DTO.GameClassResponseDto;
import com.minecraft_wiki.backend.Mapper.GameClassMapper;
import com.minecraft_wiki.backend.Service.GameClassService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/classes")
@RequiredArgsConstructor
@Tag(name = "GameClasses", description = "Operations related to game classes")

public class GameClassController {

    private final GameClassService gameClassService;
    private final GameClassMapper gameClassMapper;

    @Operation(summary = "Returns list of all game classes")
    @ApiResponse(responseCode = "200", description = "List of game classes returned successfully")
    @GetMapping
    public Page<GameClassResponseDto> getClasses(
            @PageableDefault(size = 20, page = 0)
            @ParameterObject Pageable pageable)
    {
        return gameClassService.getGameClasses(pageable)
                .map(gameClassMapper::toResponseDto);
    }

    @Operation(summary = "Returns game class by {id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "game class found successfully"),
            @ApiResponse(responseCode = "404", description = "game class not found", content = @Content)
    })
    @GetMapping("/{id}")
    public GameClassDetailsDto getClassById(
            @Parameter(description = "Game class id", required = true)
            @PathVariable("id") String gameClassId)
    {
        return gameClassMapper.toDetailsDto(gameClassService.getGameClassById(gameClassId));
    }

}
