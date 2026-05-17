package com.minecraft_wiki.backend.Mapper;

import com.minecraft_wiki.backend.DTO.MobCreateDto;
import com.minecraft_wiki.backend.DTO.MobDetailsDto;
import com.minecraft_wiki.backend.DTO.MobResponseDto;
import com.minecraft_wiki.backend.DTO.MobUpdateDto;
import com.minecraft_wiki.backend.Model.Mob;
import org.springframework.stereotype.Component;

@Component
public class MobMapper {
    public MobResponseDto toResponseDto(Mob mob) {
        if (mob == null) {
            return null;
        }

        return MobResponseDto.builder()
                .mobId(mob.getMobId().toHexString())
                .name(mob.getName())
                .imageUrl(mob.getImageUrl())
                .build();
    }

    public MobDetailsDto toDetailsDto(Mob mob) {
        if (mob == null) {
            return null;
        }

        return MobDetailsDto.builder()
                .mobId(mob.getMobId().toHexString())
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

    public Mob fromCreateDto(MobCreateDto dto) {

        if (dto == null) {
            return null;
        }

        return Mob.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .imageUrl(dto.getImageUrl())
                .locationInfo(dto.getLocationInfo())
                .stats(dto.getStats())
                .drops(dto.getDrops())
                .extraInfo(dto.getExtraInfo())
                .strength(dto.getStrength())
                .type(dto.getType())
                .build();
    }

    public void updateMobFromDto(MobUpdateDto dto, Mob mob) {

        if (dto == null || mob == null) {
            return;
        }

        if (dto.getName() != null) {
            mob.setName(dto.getName());
        }

        if (dto.getDescription() != null) {
            mob.setDescription(dto.getDescription());
        }

        if (dto.getImageUrl() != null) {
            mob.setImageUrl(dto.getImageUrl());
        }

        if (dto.getLocationInfo() != null) {
            mob.setLocationInfo(dto.getLocationInfo());
        }

        if (dto.getStats() != null) {
            mob.setStats(dto.getStats());
        }

        if (dto.getDrops() != null) {
            mob.setDrops(dto.getDrops());
        }

        if (dto.getExtraInfo() != null) {
            mob.setExtraInfo(dto.getExtraInfo());
        }

        if (dto.getStrength() != null) {
            mob.setStrength(dto.getStrength());
        }

        if (dto.getType() != null) {
            mob.setType(dto.getType());
        }
    }
}
