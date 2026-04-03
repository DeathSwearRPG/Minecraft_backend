package com.minecraft_wiki.backend.Mapper;

import com.minecraft_wiki.backend.DTO.MobDetailsDto;
import com.minecraft_wiki.backend.DTO.MobResponseDto;
import com.minecraft_wiki.backend.Model.Mob;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MobMapper {
    public MobResponseDto toResponseDto(Mob mob) {
        if (mob == null) {
            return null;
        }

        return MobResponseDto.builder()
                .mobId(mob.getMobId())
                .name(mob.getName())
                .imageUrl(mob.getImageUrl())
                .build();
    }

    public MobDetailsDto toDetailsDto(Mob mob) {
        if (mob == null) {
            return null;
        }

        return MobDetailsDto.builder()
                .mobId(mob.getMobId())
                .name(mob.getName())
                .description(mob.getDescription())
                .imageUrl(mob.getImageUrl())
                .locationInfo(mob.getLocationInfo())
                .stats(mob.getStats())
                .drops(mob.getDrops())
                .extraInfo(mob.getExtraInfo())
                .strength(mob.getStrength())
                .type(mob.getType())
                .build();
    }

    public List<MobResponseDto> toResponseDtoList(List<Mob> mobs) {
        if (mobs == null) {
            return List.of();
        }

        return mobs.stream()
                .map(this::toResponseDto)
                .toList();
    }
}
