package at.fhv.ecommerce.domain.cart;

import at.fhv.ecommerce.domain.user.User;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    private List<CartItem> items = new ArrayList<>();

    public Cart(){}

    public Long getId(){ return id; }

    public User getUser(){ return user; }

    public List<CartItem> getItems(){ return items; }

    public void setUser(User user){ this.user = user; }

    public void setItems(List<CartItem> items){ this.items = items; }
}