package com.minecraft_wiki.backend.Model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Schema(description = "Single slot in crafting grid")
public class CraftSlot {
    @Schema(description = "Item ID required in this slot", example = "69d8be6a57475d712640e520")
    private ObjectId itemId;
}
