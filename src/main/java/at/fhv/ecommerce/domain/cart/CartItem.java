package at.fhv.ecommerce.domain.cart;

import at.fhv.ecommerce.domain.product.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

@Entity
public class CartItem {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Product product;

    @Min(1)
    private int quantity;

    public CartItem(){}

    public Product getProduct(){ return product; }

    public int getQuantity(){ return quantity; }

    public void setProduct(Product product){ this.product = product; }

    public void setQuantity(int quantity){ this.quantity = quantity; }
}