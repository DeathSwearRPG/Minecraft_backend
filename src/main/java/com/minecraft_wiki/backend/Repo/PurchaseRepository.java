package com.minecraft_wiki.backend.Repo;

import com.minecraft_wiki.backend.Model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    Optional<Purchase> findByTransactionId(String transactionId);
}
