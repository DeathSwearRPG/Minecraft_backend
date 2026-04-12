package com.minecraft_wiki.backend.DTO;

import com.minecraft_wiki.backend.Model.DropItem;
import com.minecraft_wiki.backend.Model.MobStats;
import com.minecraft_wiki.backend.Model.enums.MobStrength;
import com.minecraft_wiki.backend.Model.enums.MobType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class MobDetailsDto {
    private String mobId;

    private String name;
    private String description;
    private String imageUrl;
    private String locationInfo;

    private MobStats stats;

    private List<DropItem> drops;
    private List<String> extraInfo;

    private MobStrength strength;
    private MobType type;
}
