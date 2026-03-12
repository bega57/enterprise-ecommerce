package at.fhv.ecommerce.domain.product;

import jakarta.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private double price;

    private int stock;

    public Product() {}

    public Product(String name, double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public Long getId() { return id; }

    public String getName() { return name; }

    public double getPrice() { return price; }

    public int getStock() { return stock; }

    public void setName(String name) { this.name = name; }

    public void setPrice(double price) { this.price = price; }

    public void setStock(int stock) { this.stock = stock; }
}