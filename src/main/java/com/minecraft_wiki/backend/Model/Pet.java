package com.minecraft_wiki.backend.Model;

import com.minecraft_wiki.backend.Model.enums.MobType;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@NoArgsConstructor
@SuperBuilder
@Document(collection = "mobs")

public class Pet extends BaseMob {

}
