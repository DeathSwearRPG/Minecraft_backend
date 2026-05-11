package com.minecraft_wiki.backend.Controller;

import com.minecraft_wiki.backend.DTO.ItemDetailsDto;
import com.minecraft_wiki.backend.DTO.ItemResponseDto;
import com.minecraft_wiki.backend.Mapper.ItemMapper;
import com.minecraft_wiki.backend.Model.enums.ItemRarity;
import com.minecraft_wiki.backend.Model.enums.ItemType;
import com.minecraft_wiki.backend.Model.enums.SpecialCharacteristic;
import com.minecraft_wiki.backend.Service.ItemService;
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
@RequestMapping("/api/wiki/items")
@RequiredArgsConstructor
@Tag(name = "Items", description = "Operations related to items")

public class ItemController {

    private final ItemService itemService;
    private final ItemMapper itemMapper;

    @Operation(summary = "Returns list of all items")
    @ApiResponse(responseCode = "200", description = "List of items returned successfully")
    @GetMapping()
    public Page<ItemResponseDto> getAllItems(
            @RequestParam(required = false) String search,
            @Parameter(description = "Filter items by rarity", example = "COMMON")
            @RequestParam(required = false) ItemRarity rarity,
            @Parameter(description = "Filter items by type", example = "MATERIAL")
            @RequestParam(required = false) ItemType type,
            @Parameter(description = "Filter items by characteristic", example = "SCALABLE")
            @RequestParam(required = false) SpecialCharacteristic specialCharacteristic,
            @PageableDefault(size = 20, page = 0)
            @ParameterObject Pageable pageable)
    {
        return itemService.getItems(search, type, rarity, specialCharacteristic, pageable)
                .map(itemMapper::toResponseDto);
    }

    @Operation(summary = "Returns item by {id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Item found successfully"),
            @ApiResponse(responseCode = "404", description = "Item not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ItemDetailsDto getItemById(
            @Parameter(description = "Item id", required = true)
            @PathVariable("id") String itemId)
    {
        return itemMapper.toDetailsDto(itemService.getItemById(itemId));
    }

}
