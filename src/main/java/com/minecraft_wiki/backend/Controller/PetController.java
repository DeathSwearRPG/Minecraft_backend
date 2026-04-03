package com.minecraft_wiki.backend.Controller;

import com.minecraft_wiki.backend.DTO.PetDetailsDto;
import com.minecraft_wiki.backend.DTO.PetResponseDto;
import com.minecraft_wiki.backend.Mapper.MobMapper;
import com.minecraft_wiki.backend.Mapper.PetMapper;
import com.minecraft_wiki.backend.Model.Pet;
import com.minecraft_wiki.backend.Service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/pets")
@RequiredArgsConstructor

public class PetController {

    private final PetService petService;
    private final PetMapper petMapper;

    @GetMapping
    public List<PetResponseDto> getAllPets() {
        return petMapper.toResponseDtoList(petService.getPets());
    }

    @GetMapping("/{id}")
    public PetDetailsDto getPetById(@PathVariable("id") UUID petId) {
        return petMapper.toDetailsDto(petService.getPetById(petId));
    }

}
