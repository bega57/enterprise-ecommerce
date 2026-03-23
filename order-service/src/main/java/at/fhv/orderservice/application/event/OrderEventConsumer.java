package at.fhv.orderservice.application.event;

import at.fhv.orderservice.domain.model.order.Order;
import at.fhv.orderservice.infrastructure.persistence.order.OrderRepository;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@Component
public class OrderEventConsumer {

    private final OrderRepository orderRepository;
    private final StreamBridge streamBridge;

    public OrderEventConsumer(OrderRepository orderRepository, StreamBridge streamBridge) {
        this.orderRepository = orderRepository;
        this.streamBridge = streamBridge;
    }

    @Bean
    public Consumer<StockReservedEvent> handleStockReserved() {
        return event -> {
            System.out.println("✅ STOCK RESERVED for order " + event.orderId);

            Order order = orderRepository.findById(event.orderId).orElseThrow();
            order.setStatus("CONFIRMED");
            orderRepository.save(order);
        };
    }

    @Bean
    public Consumer<StockRejectedEvent> handleStockRejected() {
        return event -> {
            System.out.println("❌ STOCK REJECTED for order " + event.orderId);

            Order order = orderRepository.findById(event.orderId).orElseThrow();
            order.setStatus("CANCELED");
            orderRepository.save(order);

            Map<Long, Integer> products = new HashMap<>();
            for (var item : order.getItems()) {
                products.put(item.getProductId(), item.getQuantity());
            }

            streamBridge.send("orderCanceled-out-0",
                    new OrderCanceledEvent(order.getId(), products));
        };
    }
}
