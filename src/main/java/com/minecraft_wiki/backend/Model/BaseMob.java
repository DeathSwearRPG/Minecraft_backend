package com.minecraft_wiki.backend.Model;

import com.minecraft_wiki.backend.Model.enums.MobStrength;
import com.minecraft_wiki.backend.Model.enums.MobType;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

public abstract class BaseMob {
    @Id
    protected ObjectId mobId;

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
