package at.fhv.orderservice.domain.model.cart;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart {

    @Id
    @GeneratedValue
    private Long id;

    private Long userId;

    @OneToMany(cascade = CascadeType.ALL)
    private List<CartItem> items = new ArrayList<>();

    public Cart(){}

    public Long getId(){ return id; }

    public Long getUserId(){ return userId; }

    public List<CartItem> getItems(){ return items; }

    public void setUserId(Long userId){ this.userId = userId; }

    public void setItems(List<CartItem> items){ this.items = items; }
}