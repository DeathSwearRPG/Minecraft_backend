package com.minecraft_wiki.backend.Controller;

import com.minecraft_wiki.backend.Service.BossService;
import com.minecraft_wiki.backend.Model.Boss;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/bosses")
public class BossController {

    private final BossService bossService;

    public BossController(BossService bossService) {
        this.bossService = bossService;
    }

    @GetMapping
    public List<Boss> getAllBosses() {
        return bossService.getBosses();
    }

    @GetMapping("/{id}")
    public Boss getBossById(@PathVariable("id") UUID bossId) {
        return bossService.getBossById(bossId);
    }

}
