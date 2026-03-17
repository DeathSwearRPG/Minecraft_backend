package com.minecraft_wiki.backend.Model;

import com.minecraft_wiki.backend.Model.enums.MobType;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder

public class Pet extends BaseMob {
    @Builder.Default
    private MobType type = MobType.PET;
}
