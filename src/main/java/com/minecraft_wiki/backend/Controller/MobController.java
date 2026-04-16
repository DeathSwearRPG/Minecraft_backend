package com.minecraft_wiki.backend.Controller;

import com.minecraft_wiki.backend.DTO.MobDetailsDto;
import com.minecraft_wiki.backend.DTO.MobResponseDto;
import com.minecraft_wiki.backend.Mapper.MobMapper;
import com.minecraft_wiki.backend.Model.enums.MobStrength;
import com.minecraft_wiki.backend.Model.enums.MobType;
import com.minecraft_wiki.backend.Service.MobService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/mobs")
@RequiredArgsConstructor
@Tag(name = "Mobs", description = "Operations related to mobs")

public class MobController {

    private final MobService mobService;
    private final MobMapper mobMapper;

    @Operation(summary = "Returns list of all mobs")
    @ApiResponse(responseCode = "200", description = "List of mobs returned successfully")
    @GetMapping
    public List<MobResponseDto> getAllMobs(
            @Parameter(description = "Filter mobs by strength", example = "NORMAL")
            @RequestParam(required = false) MobStrength strength,
            @Parameter(description = "Filter mobs by type", example = "MONSTER")
            @RequestParam(required = false) MobType type)
    {
        return mobMapper.toResponseDtoList(mobService.getMobs(strength,type));
    }

    @Operation(summary = "Returns mob by {id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Mob found successfully"),
            @ApiResponse(responseCode = "404", description = "Mob not found", content = @Content)
    })
    @GetMapping("/{id}")
    public MobDetailsDto getMobById(
            @Parameter(description = "Mob id", required = true)
            @PathVariable("id") String mobId) {
        return mobMapper.toDetailsDto(mobService.getMobById(mobId));
    }
}
