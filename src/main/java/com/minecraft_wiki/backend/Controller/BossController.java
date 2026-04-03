package com.minecraft_wiki.backend.Controller;

import com.minecraft_wiki.backend.DTO.BossDetailsDto;
import com.minecraft_wiki.backend.DTO.BossResponseDto;
import com.minecraft_wiki.backend.Mapper.BossMapper;
import com.minecraft_wiki.backend.Service.BossService;
import com.minecraft_wiki.backend.Model.Boss;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/bosses")
@RequiredArgsConstructor

public class BossController {

    private final BossService bossService;
    private final BossMapper bossMapper;

    @GetMapping
    public List<BossResponseDto> getAllBosses() {
        return bossMapper.toResponseDtoList(bossService.getBosses());
    }

    @GetMapping("/{id}")
    public BossDetailsDto getBossById(@PathVariable("id") UUID bossId) {
        return bossMapper.toDetailsDto(bossService.getBossById(bossId));
    }

}
