package at.fhv.productservice.application.event;

public class StockRejectedEvent {
    public Long orderId;
    public String reason;

    public StockRejectedEvent(Long orderId, String reason) {
        this.orderId = orderId;
        this.reason = reason;
    }
}
