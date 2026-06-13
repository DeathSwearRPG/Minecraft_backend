package com.minecraft_wiki.backend.Controller;

import com.minecraft_wiki.backend.Service.CurrentUserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    @Value("${keycloak.logout-uri}")
    private String logoutUri;

    @Value("${keycloak.post-logout-redirect-uri}")
    private String postLogoutUri;

    @Value("${spring.security.oauth2.client.registration.keycloak.client-id}")
    private String clientId;

    private final CurrentUserService currentUserService;

    @GetMapping("/login")
    public void login(
            @RequestParam(required = false) String redirect,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        if (redirect != null && isAllowedFrontendRedirect(redirect)) {
            request.getSession().setAttribute("FRONTEND_REDIRECT_URI", redirect);
        }

        response.sendRedirect("/oauth2/authorization/keycloak");
    }

    private boolean isAllowedFrontendRedirect(String redirect) {
        try {
            URI uri = URI.create(redirect);

            return "http".equals(uri.getScheme())
                    && "localhost".equals(uri.getHost())
                    && uri.getPort() > 0
                    && uri.getPath().startsWith("/admin");
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @GetMapping("/me")
    public Map<String, Object> getUserInfo(@AuthenticationPrincipal OidcUser user) {
        return currentUserService.getCurrentUserInfo(user);
    }

    @PostMapping("/logout")
    public void logout(@AuthenticationPrincipal OidcUser user,
                       HttpServletRequest request,
                       HttpServletResponse response
    ) throws Exception, ServletException {
        String idToken = user != null && user.getIdToken() != null
                ? user.getIdToken().getTokenValue()
                : null;

        request.logout();

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(logoutUri)
                .queryParam("client_id", clientId)
                .queryParam("post_logout_redirect_uri", postLogoutUri);

        if (idToken != null) {
            builder.queryParam("id_token_hint", idToken);
        }

        response.sendRedirect(builder.build().encode().toUriString());
    }
}
