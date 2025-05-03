package com.martinatanasov.cool.services;

import com.martinatanasov.parent.events.DrinkRequestEvent;

public interface DrinkRequestProcessor {

    void processDrinkRequest(DrinkRequestEvent event);

}
