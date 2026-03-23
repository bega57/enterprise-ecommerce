package at.fhv.orderservice.application.event;

import java.util.Map;

public class OrderCreatedEvent {
    public Long orderId;
    public Map<Long, Integer> products;
}
