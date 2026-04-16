package com.minecraft_wiki.backend.Model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Schema(description = "Crafting recipe consisting of slots")
public class CraftRecipe {
    @Schema(description = "List of crafting slots (3x3 grid representation)")
    private List<CraftSlot> slots;
}
