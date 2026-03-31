package at.fhv.ecommerce.presentation.rest.controller;

import at.fhv.ecommerce.infrastructure.client.OrderClient;
import at.fhv.ecommerce.infrastructure.client.ProductClient;
import at.fhv.ecommerce.presentation.ui.dto.OrderResponseDTO;
import at.fhv.ecommerce.presentation.ui.dto.ProductDTO;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/aggregate")
public class AggregationController {

    private final OrderClient orderClient;
    private final ProductClient productClient;

    public AggregationController(OrderClient orderClient, ProductClient productClient) {
        this.orderClient = orderClient;
        this.productClient = productClient;
    }

    @GetMapping("/orders/{id}")
    public Map<String, Object> getOrderWithProduct(@PathVariable Long id) {

        OrderResponseDTO order = orderClient.getOrderById(id);

        Long productId = order.getItems().get(0).getProductId();

        ProductDTO product = productClient.getProductById(productId);

        return Map.of(
                "order", order,
                "product", product
        );
    }
}