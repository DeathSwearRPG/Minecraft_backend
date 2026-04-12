package com.minecraft_wiki.backend.Controller;

import com.minecraft_wiki.backend.DTO.ItemDetailsDto;
import com.minecraft_wiki.backend.DTO.ItemResponseDto;
import com.minecraft_wiki.backend.Mapper.ItemMapper;
import com.minecraft_wiki.backend.Model.enums.ItemRarity;
import com.minecraft_wiki.backend.Model.enums.ItemType;
import com.minecraft_wiki.backend.Model.enums.SpecialCharacteristic;
import com.minecraft_wiki.backend.Service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor

public class ItemController {

    private final ItemService itemService;
    private final ItemMapper itemMapper;

    @GetMapping
    public List<ItemResponseDto> getAllItems(@RequestParam(required = false) ItemType type,
                                             @RequestParam(required = false) ItemRarity rarity,
                                             @RequestParam(required = false) SpecialCharacteristic special) {
        return itemMapper.toResponseDtoList(itemService.getItems(rarity,type,special));
    }

    @GetMapping("/{id}")
    public ItemDetailsDto getItemById(@PathVariable("id") String itemId) {
        return itemMapper.toDetailsDto(itemService.getItemById(itemId));
    }

}
