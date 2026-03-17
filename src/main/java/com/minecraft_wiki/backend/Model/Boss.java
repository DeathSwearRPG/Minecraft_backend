package com.minecraft_wiki.backend.Model;

import com.minecraft_wiki.backend.Model.enums.MobStrength;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder

public class Boss extends BaseMob {
    @Builder.Default
    private MobStrength strength = MobStrength.BOSS;
}
