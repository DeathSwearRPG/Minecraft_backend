package com.minecraft_wiki.backend.DTO;

import com.minecraft_wiki.backend.Model.DropItem;
import com.minecraft_wiki.backend.Model.MobStats;
import com.minecraft_wiki.backend.Model.enums.MobStrength;
import com.minecraft_wiki.backend.Model.enums.MobType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "data to create mob")
public class MobCreateDto {

    @Schema(description = "name of mob", example = "Zombie")
    private String name;

    @Schema(description = "information about mob", example = "Обычный медленный зомби")
    private String description;

    @Schema(description = "Url to get image of mob", example = "https://example.com/zombie.png")
    private String imageUrl;

    @Schema(description = "Location where the mob can be found", example = "Ночь, равнины")
    private String locationInfo;

    @Schema(description = "Stats of the mob (health, damage, armor, growth per level)")
    private MobStats stats;

    @Schema(description = "List of items dropped by the mob")
    private List<DropItem> drops;

    @Schema(description = "Additional information about the mob", example = "Боится солнца")
    private List<String> extraInfo;

    @Schema(description = "Strength level of the mob", example = "ELITE")
    private MobStrength strength;

    @Schema(description = "Type of the mob", example = "MONSTER")
    private MobType type;

}
