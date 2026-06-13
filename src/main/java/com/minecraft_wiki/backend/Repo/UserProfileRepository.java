package com.minecraft_wiki.backend.Repo;

import com.minecraft_wiki.backend.Model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    Optional<UserProfile> findByKeycloakSub(String keycloakSub);
}
