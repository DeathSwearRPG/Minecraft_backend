package com.minecraft_wiki.backend.Controller;

import com.minecraft_wiki.backend.DTO.BossDetailsDto;
import com.minecraft_wiki.backend.DTO.BossResponseDto;
import com.minecraft_wiki.backend.Mapper.BossMapper;
import com.minecraft_wiki.backend.Model.enums.MobType;
import com.minecraft_wiki.backend.Service.BossService;
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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wiki/bosses")
@RequiredArgsConstructor
@Tag(name = "Bosses", description = "Operations related to bosses")

public class BossController {

    private final BossService bossService;
    private final BossMapper bossMapper;

    @Operation(summary = "Returns list of all bosses")
    @ApiResponse(responseCode = "200", description = "List of bosses returned successfully")
    @GetMapping
    public Page<BossResponseDto> getAllBosses(
            @RequestParam(required = false) String search,
            @Parameter(description = "Filter bosses by type", example = "MONSTER")
            @RequestParam(required = false) MobType type,
            @PageableDefault(size = 20, page = 0)
            @ParameterObject Pageable pageable)
    {
        return bossService.getBosses(search, type, pageable)
                .map(bossMapper::toResponseDto);
    }

    @Operation(summary = "Returns boss by {id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Boss found successfully"),
            @ApiResponse(responseCode = "404", description = "Boss not found", content = @Content)
    })
    @GetMapping("/{id}")
    public BossDetailsDto getBossById(
            @Parameter(description = "Boss id", required = true)
            @PathVariable("id") String bossId)
    {
        return bossMapper.toDetailsDto(bossService.getBossById(bossId));
    }

}
