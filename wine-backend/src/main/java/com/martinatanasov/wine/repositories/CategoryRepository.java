package com.martinatanasov.wine.repositories;

import com.martinatanasov.wine.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
