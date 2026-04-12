package com.minecraft_wiki.backend.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CraftSlot {
    private ObjectId itemId;
}
