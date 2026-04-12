package com.minecraft_wiki.backend.Mapper;

import com.minecraft_wiki.backend.DTO.PetDetailsDto;
import com.minecraft_wiki.backend.DTO.PetResponseDto;
import com.minecraft_wiki.backend.Model.Pet;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PetMapper {
    public PetResponseDto toResponseDto(Pet pet) {
        if (pet == null) {
            return null;
        }

        return PetResponseDto.builder()
                .mobId(pet.getMobId().toHexString())
                .name(pet.getName())
                .imageUrl(pet.getImageUrl())
                .build();
    }

    public PetDetailsDto toDetailsDto(Pet pet) {
        if (pet == null) {
            return null;
        }

        return PetDetailsDto.builder()
                .mobId(pet.getMobId().toHexString())
                .name(pet.getName())
                .description(pet.getDescription())
                .imageUrl(pet.getImageUrl())
                .locationInfo(pet.getLocationInfo())
                .stats(pet.getStats())
                .extraInfo(pet.getExtraInfo())
                .strength(pet.getStrength())
                .type(pet.getType())
                .build();
    }

    public List<PetResponseDto> toResponseDtoList(List<Pet> pets) {
        if (pets == null) {
            return List.of();
        }
        return pets.stream()
                .map(this::toResponseDto)
                .toList();
    }
}
