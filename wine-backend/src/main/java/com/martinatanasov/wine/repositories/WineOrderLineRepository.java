package com.martinatanasov.wine.repositories;

import com.martinatanasov.wine.entity.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WineOrderLineRepository extends JpaRepository<OrderLine, UUID> {
}
