package com.minecraft_wiki.backend.Controller;

import com.minecraft_wiki.backend.DTO.PetDetailsDto;
import com.minecraft_wiki.backend.DTO.PetResponseDto;
import com.minecraft_wiki.backend.Mapper.PetMapper;
import com.minecraft_wiki.backend.Model.enums.MobStrength;
import com.minecraft_wiki.backend.Service.PetService;
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
@RequestMapping("/api/wiki/pets")
@RequiredArgsConstructor
@Tag(name = "Pets", description = "Operations related to pets")

public class PetController {

    private final PetService petService;
    private final PetMapper petMapper;

    @Operation(summary = "Returns list of all pets")
    @ApiResponse(responseCode = "200", description = "List of pets returned successfully")
    @GetMapping
    public Page<PetResponseDto> getAllPets(
            @Parameter(description = "Filter pets by strength", example = "WEAK")
            @RequestParam(required = false) MobStrength strength,
            @PageableDefault(size = 20, page = 0)
            @ParameterObject Pageable pageable)
    {
        return petService.getPets(strength, pageable)
                .map(petMapper::toResponseDto);
    }

    @Operation(summary = "Returns pet by {id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pet found successfully"),
            @ApiResponse(responseCode = "404", description = "Pet not found", content = @Content)
    })
    @GetMapping("/{id}")
    public PetDetailsDto getPetById(
            @Parameter(description = "Pet id", required = true)
            @PathVariable("id") String petId)
    {
        return petMapper.toDetailsDto(petService.getPetById(petId));
    }

}
