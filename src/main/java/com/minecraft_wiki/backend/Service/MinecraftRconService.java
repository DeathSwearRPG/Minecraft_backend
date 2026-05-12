package com.minecraft_wiki.backend.Service;

import org.glavo.rcon.Rcon;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MinecraftRconService {

    @Value("${minecraftServer.rcon.host}")
    private String host;

    @Value("${minecraftServer.rcon.port}")
    private int port;

    @Value("${minecraftServer.rcon.password}")
    private String password;

    public String sendCommand(String command) {
        try (Rcon rcon = new Rcon(
                host,
                port,
                password
        )) {
            return rcon.command(command);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send RCON command", e);
        }
    }

    public String grantTestBonus(String nickname) {
        return sendCommand("say Granted bonus to " + nickname);
    }

}
