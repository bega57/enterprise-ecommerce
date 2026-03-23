package at.fhv.orderservice.application.event;

public class StockReservedEvent {
    public Long orderId;

    public StockReservedEvent() {}

    public StockReservedEvent(Long orderId) {
        this.orderId = orderId;
    }
}
