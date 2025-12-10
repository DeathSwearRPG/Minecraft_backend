package com.minecraft_wiki.backend.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {

    @GetMapping("/api/info")
    public String getInfo() {
        return "Hello world";
    }

}
