package com.minecraft_wiki.backend.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Boss {
    private UUID bossId;
    private String name;
    private int health;
    private int armor;
    private int damage;
    private String size;
}
