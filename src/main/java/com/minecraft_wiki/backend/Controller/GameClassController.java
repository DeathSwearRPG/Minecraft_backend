package com.minecraft_wiki.backend.Controller;

import com.minecraft_wiki.backend.Model.GameClass;
import com.minecraft_wiki.backend.Service.GameClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/classes")
@RequiredArgsConstructor

public class GameClassController {

    private final GameClassService gameClassService;

    @GetMapping
    public List<GameClass> getClasses() {
        return gameClassService.getClasses();
    }

    @GetMapping("/{id}")
    public GameClass getClassById(@PathVariable("id") UUID gameClassId) {
        return gameClassService.getClassById(gameClassId);
    }

}
