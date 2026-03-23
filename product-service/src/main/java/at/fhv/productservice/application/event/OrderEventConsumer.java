package at.fhv.productservice.application.event;

import at.fhv.productservice.domain.model.product.Product;
import at.fhv.productservice.infrastructure.persistence.product.ProductRepository;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.Consumer;

@Component
public class OrderEventConsumer {

    private final ProductRepository productRepository;
    private final StreamBridge streamBridge;

    public OrderEventConsumer(ProductRepository productRepository,
                              StreamBridge streamBridge) {
        this.productRepository = productRepository;
        this.streamBridge = streamBridge;
    }

    @Bean
    public Consumer<OrderCreatedEvent> handleOrderCreated() {
        return event -> {

            boolean available = true;

            for (Map.Entry<Long, Integer> entry : event.products.entrySet()) {
                Product product = productRepository.findById(entry.getKey()).orElseThrow();

                if (product.getStock() < entry.getValue()) {
                    available = false;
                    break;
                }
            }

            if (available) {
                for (Map.Entry<Long, Integer> entry : event.products.entrySet()) {
                    Product product = productRepository.findById(entry.getKey()).orElseThrow();
                    product.setStock(product.getStock() - entry.getValue());
                    productRepository.save(product);
                }

                streamBridge.send("stockReserved-out-0",
                        new StockReservedEvent(event.orderId));

            } else {
                streamBridge.send("stockRejected-out-0",
                        new StockRejectedEvent(event.orderId, "Not enough stock"));
            }
        };
    }

    @Bean
    public Consumer<OrderCanceledEvent> handleOrderCanceled() {
        return event -> {

            for (Map.Entry<Long, Integer> entry : event.products.entrySet()) {
                Product product = productRepository.findById(entry.getKey()).orElseThrow();

                product.setStock(product.getStock() + entry.getValue());
                productRepository.save(product);
            }

            System.out.println("♻️ Stock restored for order " + event.orderId);
        };
    }
}