package com.minecraft_wiki.backend.Controller;

import com.minecraft_wiki.backend.DTO.MobDetailsDto;
import com.minecraft_wiki.backend.DTO.MobResponseDto;
import com.minecraft_wiki.backend.Mapper.MobMapper;
import com.minecraft_wiki.backend.Model.BaseMob;
import com.minecraft_wiki.backend.Model.Mob;
import com.minecraft_wiki.backend.Model.enums.MobStrength;
import com.minecraft_wiki.backend.Model.enums.MobType;
import com.minecraft_wiki.backend.Service.MobService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/mobs")
@RequiredArgsConstructor
public class MobController {

    private final MobService mobService;
    private final MobMapper mobMapper;

    @GetMapping
    public List<MobResponseDto> getMobService(@RequestParam(required = false) MobStrength strength,
                                              @RequestParam(required = false)MobType type) {
        return mobMapper.toResponseDtoList(mobService.getAllMobs(strength,type));
    }
    @GetMapping("/{id}")
    public MobDetailsDto getMobById(@PathVariable("id") UUID mobId) {
        return mobMapper.toDetailsDto(mobService.getMobById(mobId));
    }
}
