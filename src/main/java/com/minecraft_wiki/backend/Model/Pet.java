package com.minecraft_wiki.backend.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Pet {
    private UUID petId;
    private String name;
    private String description;
    private String behavior;
    private int health;
    private int armor;
    private int damage;
}
