package at.fhv.orderservice.application.event;

public class StockRejectedEvent {
    public Long orderId;
    public String reason;

    public StockRejectedEvent() {}

    public StockRejectedEvent(Long orderId, String reason) {
        this.orderId = orderId;
        this.reason = reason;
    }
}
