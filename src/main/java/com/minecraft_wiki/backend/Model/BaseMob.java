package com.minecraft_wiki.backend.Model;

import com.minecraft_wiki.backend.Model.enums.MobStrength;
import com.minecraft_wiki.backend.Model.enums.MobType;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data

public abstract class BaseMob {
    protected UUID mobId;

    protected String name;
    protected String description;
    protected String imageUrl;
    protected String locationInfo;

    protected MobStats stats;

    protected List<DropItem> drops;
    protected List<String> extraInfo;

    protected MobStrength strength;
    protected MobType type;


}
