package com.minecraft_wiki.backend.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class DropItem {
    private String itemName;
    private int minCount;
    private int maxCount;
    private double chance;
}
