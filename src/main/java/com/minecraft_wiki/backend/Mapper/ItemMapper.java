package com.minecraft_wiki.backend.Mapper;

import com.minecraft_wiki.backend.DTO.ItemDetailsDto;
import com.minecraft_wiki.backend.DTO.ItemResponseDto;
import com.minecraft_wiki.backend.Model.Item;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemMapper {
    public ItemResponseDto toResponseDto(Item item) {
        if (item == null) {
            return null;
        }

        return ItemResponseDto.builder()
                .itemId(item.getItemId())
                .name(item.getName())
                .imageUrl(item.getImageUrl())
                .build();
    }

    public ItemDetailsDto toDetailsDto(Item item) {
        if (item == null) {
            return null;
        }
        return ItemDetailsDto.builder()
                .itemId(item.getItemId())
                .name(item.getName())
                .description(item.getDescription())
                .imageUrl(item.getImageUrl())
                .itemType(item.getItemType())
                .itemRarity(item.getItemRarity())
                .specialCharacteristic(item.getSpecialCharacteristic())
                .itemStats(item.getItemStats())
                .craftRecipe(item.getCraftRecipe())
                .build();
    }

    public List<ItemResponseDto> toResponseDtoList(List<Item> items) {
        if (items == null) {
            return List.of();
        }

        return items.stream()
                .map(this::toResponseDto)
                .toList();
    }
}
