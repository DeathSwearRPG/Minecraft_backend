package com.minecraft_wiki.backend.Service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class MinecraftServerProcessService {

    private Process process;
    private BufferedWriter serverInput;
    private final AtomicBoolean serverReady = new AtomicBoolean(false);

    @PostConstruct
    public void startServer() {
        try {
            ProcessBuilder builder = new ProcessBuilder(
                    List.of(
                            "java",
                            "-jar",
                            "server.jar",
                            "nogui"
                    )
            );

            builder.directory(
                    new File("C:\\Users\\God\\Downloads\\paperDSMMORPG 1-21-1\\paperDSMMORPG 1-21-1")
            );

            builder.redirectErrorStream(true);

            this.process = builder.start();

            this.serverInput = new BufferedWriter(
                    new OutputStreamWriter(process.getOutputStream())
            );

            // Читаем stdout Minecraft
            new Thread(() -> {
                try (BufferedReader reader =
                             new BufferedReader(
                                     new InputStreamReader(process.getInputStream()))) {

                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println("[MC] " + line);

                        if (line.contains("Done") && line.contains("For help")) {
                            serverReady.set(true);
                            System.out.println("[MC] Server is READY to accept commands");
                        }
                    }
                } catch (IOException e) {
                    System.err.println("Minecraft output reader stopped");
                }
            }).start();

            System.out.println("Minecraft server process started");

        } catch (Exception e) {
            throw new RuntimeException("Failed to start Minecraft server", e);
        }
    }

    public synchronized void sendCommand(String command) {

        if (process == null || !process.isAlive()) {
            System.err.println("Minecraft process is not alive");
            return;
        }

        if (!serverReady.get()) {
            System.err.println("Minecraft server is not ready yet, skipping command");
            return;
        }

        try {
            serverInput.write(command);
            serverInput.newLine();
            serverInput.flush();

        } catch (IOException e) {
            System.err.println(
                    "Failed to send command to Minecraft: " + e.getMessage()
            );
        }
    }
}
