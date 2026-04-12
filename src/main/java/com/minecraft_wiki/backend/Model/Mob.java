package com.minecraft_wiki.backend.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@SuperBuilder
@NoArgsConstructor
@Document(collection = "mobs")

public class Mob extends BaseMob {
}
