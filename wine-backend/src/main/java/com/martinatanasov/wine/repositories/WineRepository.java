package com.martinatanasov.wine.repositories;

import com.martinatanasov.parent.model.WineType;
import com.martinatanasov.wine.entity.Wine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WineRepository extends JpaRepository<Wine, UUID> {

    Page<Wine> findAllByWineNameIsLikeIgnoreCase(String wineName, Pageable pageable);

    Page<Wine> findAllByWineType(WineType wineType, Pageable pageable);

    Page<Wine> findAllByWineNameIsLikeIgnoreCaseAndWineType(String wineName, WineType wineType, Pageable pageable);

}
