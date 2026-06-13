package com.minecraft_wiki.backend.Service;

import com.minecraft_wiki.backend.Model.UserProfile;
import com.minecraft_wiki.backend.Repo.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CurrentUserService {

    private final UserProfileRepository userProfileRepository;

    public String getKeycloakSub(OidcUser user) {
        return user.getSubject();
    }

    public String getUsername(OidcUser user) {
        return user.getPreferredUsername();
    }

    public Map<String, Object> getCurrentUserInfo(OidcUser user) {
        Map<String, Object> realmAccess = user.getClaimAsMap("realm_access");

        List<String> roles = (List<String>) realmAccess.get("roles");

        return Map.of(
                "username", user.getPreferredUsername(),
                "roles", roles
        );
    }

    public UserProfile getOrCreateProfile(OidcUser user) {

        return userProfileRepository
                .findByKeycloakSub(user.getSubject())
                .orElseGet(() -> {
                    UserProfile profile = UserProfile.builder()
                            .keycloakSub(user.getSubject())
                            .username(user.getPreferredUsername())
                            .build();

                    return userProfileRepository.save(profile);
                });
    }

    public UserProfile updateMinecraftNickname(OidcUser user, String minecraftNickname) {
        UserProfile profile = getOrCreateProfile(user);

        if (minecraftNickname == null || minecraftNickname.isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Minecraft nickname is required"
            );
        }

        profile.setUsername(user.getPreferredUsername());
        profile.setMinecraftNickname(minecraftNickname.trim());

        return userProfileRepository.save(profile);
    }
}
