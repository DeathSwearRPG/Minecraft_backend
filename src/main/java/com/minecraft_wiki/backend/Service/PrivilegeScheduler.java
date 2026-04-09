package com.minecraft_wiki.backend.Service;

import com.minecraft_wiki.backend.Model.payment.entity.PlayerPrivilegeEntity;
import com.minecraft_wiki.backend.Repo.payment.PlayerPrivilegeRepository;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
public class PrivilegeScheduler {

    private final PlayerPrivilegeRepository privilegeRepository;
    private final MinecraftCommandService minecraftCommandService;

    public PrivilegeScheduler(
            PlayerPrivilegeRepository privilegeRepository,
            MinecraftCommandService minecraftCommandService
    ) {
        this.privilegeRepository = privilegeRepository;
        this.minecraftCommandService = minecraftCommandService;
    }

    @Scheduled(fixedDelay = 10_000)
    @Transactional
    public void applyPrivileges() {

        List<PlayerPrivilegeEntity> pending =
                privilegeRepository.findByStatus("PENDING_APPLY");

        for (PlayerPrivilegeEntity privilege : pending) {
            minecraftCommandService.grantPrivilege(privilege);
            privilege.setStatus("ACTIVE");
        }
    }

    @Scheduled(fixedDelay = 15_000)
    @Transactional
    public void markExpiredPrivileges() {

        List<PlayerPrivilegeEntity> expired =
                privilegeRepository.findByEndsAtBeforeAndStatus(
                        Instant.now(),
                        "ACTIVE"
                );

        for (PlayerPrivilegeEntity privilege : expired) {
            privilege.setStatus("PENDING_REVOKE");
        }
    }

    @Scheduled(fixedDelay = 10_000)
    @Transactional
    public void revokePrivileges() {

        List<PlayerPrivilegeEntity> toRevoke =
                privilegeRepository.findByStatus("PENDING_REVOKE");

        for (PlayerPrivilegeEntity privilege : toRevoke) {
            minecraftCommandService.revokePrivilege(privilege);
            privilege.setStatus("REVOKED");
        }
    }
}
