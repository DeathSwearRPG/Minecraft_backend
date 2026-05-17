package com.minecraft_wiki.backend.Controller;

import com.minecraft_wiki.backend.DTO.MobCreateDto;
import com.minecraft_wiki.backend.DTO.MobDetailsDto;
import com.minecraft_wiki.backend.DTO.MobUpdateDto;
import com.minecraft_wiki.backend.Mapper.MobMapper;
import com.minecraft_wiki.backend.Service.MobService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/mobs")
@RequiredArgsConstructor
public class AdminMobController {

    private final MobService mobService;
    private final MobMapper mobMapper;

    @PostMapping
    public MobDetailsDto createMob(@RequestBody MobCreateDto dto) {
        return mobMapper.toDetailsDto(mobService.createMob(dto));
    }

    @PatchMapping("/{id}")
    public MobDetailsDto updateMob(
            @PathVariable String id,
            @RequestBody MobUpdateDto dto
            )
    {
        return mobMapper.toDetailsDto(mobService.updateMob(id, dto));
    }
}
