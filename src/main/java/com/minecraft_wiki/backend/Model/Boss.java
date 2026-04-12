package com.minecraft_wiki.backend.Model;

import com.minecraft_wiki.backend.Model.enums.MobStrength;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@NoArgsConstructor
@SuperBuilder
@Document(collection = "mobs")

public class Boss extends BaseMob {

}
