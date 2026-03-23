package at.fhv.orderservice.application.event;

import java.util.Map;

public class OrderCanceledEvent {
    public Long orderId;
    public Map<Long, Integer> products;

    public OrderCanceledEvent() {}

    public OrderCanceledEvent(Long orderId, Map<Long, Integer> products) {
        this.orderId = orderId;
        this.products = products;
    }
}