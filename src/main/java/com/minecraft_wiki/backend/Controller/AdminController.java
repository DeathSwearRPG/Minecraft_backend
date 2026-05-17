package com.minecraft_wiki.backend.Controller;

import com.minecraft_wiki.backend.Service.MinecraftRconService;
import com.minecraft_wiki.backend.Service.Storage.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final MinecraftRconService minecraftRconService;
    private final S3Service s3Service;

    @PostMapping("/grantBonus")
    public String bonusGranted(@RequestParam String nickname) {
        return minecraftRconService.grantTestBonus(nickname);
    }

    @PostMapping("/images/upload")
    public String uploadImage(@RequestParam("file")MultipartFile file,
                              @RequestParam(defaultValue = "images") String folder)
    {
        return s3Service.uploadFile(file, folder);
    }
}
