package com.martinatanasov.wine.events;

import com.martinatanasov.wine.entity.Wine;

public interface WineEvent {

    Wine getWine();

    String getCustomerName();

}
