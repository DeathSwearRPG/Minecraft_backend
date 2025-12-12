package com.minecraft_wiki.backend.Controller;

import com.minecraft_wiki.backend.Model.BaseMob;
import com.minecraft_wiki.backend.Model.Mob;
import com.minecraft_wiki.backend.Service.MobService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RestController
@RequestMapping("/api/mobs")
@RequiredArgsConstructor
public class MobController {

    private final MobService mobService;

    @GetMapping
    public MobService getMobService() {
        return mobService;
    }
    @GetMapping("/{id}")
    public BaseMob getMobById(@PathVariable("id") UUID mobId) {
        return mobService.getMobById(mobId);
    }
}
