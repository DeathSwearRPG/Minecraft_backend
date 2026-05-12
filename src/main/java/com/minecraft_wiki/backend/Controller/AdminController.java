package com.minecraft_wiki.backend.Controller;

import com.minecraft_wiki.backend.Service.MinecraftRconService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final MinecraftRconService minecraftRconService;

    @PostMapping("/grantBonus")
    public String bonusGranted(@RequestParam String nickname) {
        return minecraftRconService.grantTestBonus(nickname);
    }
}
