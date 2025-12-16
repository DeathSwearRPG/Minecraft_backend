package com.minecraft_wiki.backend.Controller;

import com.minecraft_wiki.backend.Model.BaseMob;
import com.minecraft_wiki.backend.Model.Mob;
import com.minecraft_wiki.backend.Model.enums.MobStrength;
import com.minecraft_wiki.backend.Model.enums.MobType;
import com.minecraft_wiki.backend.Service.MobService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/mobs")
@RequiredArgsConstructor
public class MobController {

    private final MobService mobService;

    @GetMapping
    public List<BaseMob> getMobService(@RequestParam(required = false) MobStrength strength,
                                       @RequestParam(required = false)MobType type) {
        return mobService.getAllMobs(strength, type);
    }
    @GetMapping("/{id}")
    public BaseMob getMobById(@PathVariable("id") UUID mobId) {
        return mobService.getMobById(mobId);
    }
}
