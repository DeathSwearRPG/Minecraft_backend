package com.minecraft_wiki.backend.Mapper;


import com.minecraft_wiki.backend.DTO.BossDetailsDto;
import com.minecraft_wiki.backend.DTO.BossResponseDto;
import com.minecraft_wiki.backend.Model.Boss;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BossMapper {
    public BossResponseDto toResponseDto(Boss boss) {
        if (boss == null) {
            return null;
        }

        return BossResponseDto.builder()
                .mobId(boss.getMobId().toHexString())
                .name(boss.getName())
                .imageUrl(boss.getImageUrl())
                .build();
    }

    public BossDetailsDto toDetailsDto(Boss boss) {
        if (boss == null) {
            return null;
        }

        return BossDetailsDto.builder()
                .mobId(boss.getMobId().toHexString())
                .name(boss.getName())
                .description(boss.getDescription())
                .imageUrl(boss.getImageUrl())
                .locationInfo(boss.getLocationInfo())
                .stats(boss.getStats())
                .drops(boss.getDrops())
                .extraInfo(boss.getExtraInfo())
                .strength(boss.getStrength())
                .type(boss.getType())
                .build();
     }

    public List<BossResponseDto> toResponseDtoList(List<Boss> bosses) {
        if (bosses == null) {
            return List.of();
        }

        return bosses.stream()
                .map(this::toResponseDto)
                .toList();

    }

}
