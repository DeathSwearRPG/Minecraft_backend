package com.minecraft_wiki.backend.Service;

import com.minecraft_wiki.backend.Model.payment.entity.PlayerPrivilegeEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Writer;

@Service
public class MinecraftCommandService {

    private final MinecraftServerProcessService server;

    public MinecraftCommandService(MinecraftServerProcessService server) {
        this.server = server;
    }

    public void grantPrivilege(PlayerPrivilegeEntity privilege) {
        server.sendCommand(
                "say BACKEND TEST: privilege granted to " + privilege.getPlayerName()
        );
    }

    public void revokePrivilege(PlayerPrivilegeEntity privilege) {
        server.sendCommand(
                "say BACKEND TEST: privilege " +
                        privilege.getPrivilegeCode() +
                        " revoked from " +
                        privilege.getPlayerName()
        );
    }
}

