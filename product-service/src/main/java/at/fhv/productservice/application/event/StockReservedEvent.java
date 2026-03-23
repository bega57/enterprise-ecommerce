package at.fhv.productservice.application.event;

public class StockReservedEvent {
    public Long orderId;

    public StockReservedEvent(Long orderId) {
        this.orderId = orderId;
    }
}
