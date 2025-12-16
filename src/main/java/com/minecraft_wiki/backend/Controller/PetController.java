package com.minecraft_wiki.backend.Controller;

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

    @GetMapping
    public List<Pet> getAllPets() {
        return petService.getPets();
    }

    @GetMapping("/{id}")
    public Pet getPetById(@PathVariable("id") UUID petId) {
        return petService.getPetById(petId);
    }

}
