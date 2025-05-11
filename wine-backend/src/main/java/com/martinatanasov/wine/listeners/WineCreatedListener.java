package com.martinatanasov.wine.listeners;


import com.martinatanasov.wine.entity.Audit;
import com.martinatanasov.wine.entity.Wine;
import com.martinatanasov.wine.events.*;
import com.martinatanasov.wine.repositories.AuditRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class WineCreatedListener {

    //private final WineMapper wineMapper;
    private final AuditRepository auditRepository;

    @Async
    @EventListener
    public void listen(WineEvent event) {

        //val wineAudit = wineMapper.wineToWineAudit(event.getWine());
        Audit wineAudit = getAudit(event.getWine());

        String eventType = null;

        switch (event) {
            case WineCreatedEvent wineCreatedEvent -> eventType = "WINE_CREATED";
            case WinePatchedEvent winePatchedEvent -> eventType = "WINE_PATCHED";
            case WineUpdatedEvent wineUpdatedEvent -> eventType = "WINE_UPDATED";
            case WineDeletedEvent wineDeletedEvent -> eventType = "WINE_DELETED";
            default -> eventType = "UNKNOWN";
        }

        wineAudit.setEventType(eventType);

        if (event.getCustomerName() != null) {
            wineAudit.setCustomerName(event.getCustomerName());
        }

        Audit savedWineAudit = auditRepository.save(wineAudit);
        log.debug("Wine Audit Saved: {} {}", eventType, savedWineAudit.getId());

    }

    private Audit getAudit(Wine wine) {
        return Audit.builder()
                .id(wine.getId())
                .createdDateAudit(wine.getCreatedDate())
                .price(wine.getPrice())
                .eventType(wine.getWineType()
                        .toString()
                )
                .quantity(wine.getQuantity())
                .wineName(wine.getWineName())
                .build();
    }
}
