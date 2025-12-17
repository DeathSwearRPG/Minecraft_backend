package com.minecraft_wiki.backend.Repo.payment;

import com.minecraft_wiki.backend.Model.payment.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository
        extends JpaRepository<ProductEntity, String> {
}
