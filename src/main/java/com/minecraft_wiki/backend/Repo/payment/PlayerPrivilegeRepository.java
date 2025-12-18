package com.minecraft_wiki.backend.Repo.payment;

import com.minecraft_wiki.backend.Model.payment.entity.PlayerPrivilegeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public interface PlayerPrivilegeRepository
        extends JpaRepository<PlayerPrivilegeEntity, UUID> {

    List<PlayerPrivilegeEntity> findByStatus(String status);

    List<PlayerPrivilegeEntity> findByEndsAtBeforeAndStatus(
            Instant time,
            String status
    );
}
