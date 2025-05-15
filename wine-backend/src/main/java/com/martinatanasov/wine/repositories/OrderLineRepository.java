package com.martinatanasov.wine.repositories;

import com.martinatanasov.wine.entity.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderLineRepository extends JpaRepository<OrderLine, UUID> {
}
