package com.minecraft_wiki.backend.Controller;

import com.minecraft_wiki.backend.Model.UserProfile;
import com.minecraft_wiki.backend.Service.CurrentUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final CurrentUserService currentUserService;

    @GetMapping
    public UserProfile getUser(@AuthenticationPrincipal OidcUser user) {
        return currentUserService.getOrCreateProfile(user);
    }

    @PatchMapping
    public UserProfile updateUser(@AuthenticationPrincipal OidcUser user, @RequestBody UserProfile request) {
        return currentUserService.updateMinecraftNickname(user, request.getMinecraftNickname());
    }

}
