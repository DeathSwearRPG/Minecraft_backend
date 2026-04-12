package com.minecraft_wiki.backend.Controller;

import com.minecraft_wiki.backend.DTO.MobDetailsDto;
import com.minecraft_wiki.backend.DTO.MobResponseDto;
import com.minecraft_wiki.backend.Mapper.MobMapper;
import com.minecraft_wiki.backend.Model.enums.MobStrength;
import com.minecraft_wiki.backend.Model.enums.MobType;
import com.minecraft_wiki.backend.Service.MobService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/mobs")
@RequiredArgsConstructor
public class MobController {

    private final MobService mobService;
    private final MobMapper mobMapper;

    @GetMapping
    public List<MobResponseDto> getAllMobs(@RequestParam(required = false) MobStrength strength,
                                              @RequestParam(required = false)MobType type) {
        return mobMapper.toResponseDtoList(mobService.getMobs(strength,type));
    }
    @GetMapping("/{id}")
    public MobDetailsDto getMobById(@PathVariable("id") String mobId) {
        return mobMapper.toDetailsDto(mobService.getMobById(mobId));
    }
}
