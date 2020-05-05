package com.mty.cisp.asnyc;

import java.util.List;

public interface EventHandler {
    void doHandle(EventModel model);
    List<EventType> getSupportEventTypes();
}
