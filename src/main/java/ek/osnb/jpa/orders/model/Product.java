package ek.osnb.jpa.orders.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private double price;

    @ManyToMany
    @JoinTable(
            name = "product_category",
    joinColumns = @JoinColumn(name = "product_id"),
    inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    public Product() {
        // Default constructor
    }

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    /* Holder bidirektionel relation i sync (i hukommelsen):
    * ingen dubletter i join-tabellen,
    * og at vi kun synker den anden side én gang. */
    public void addCategory(Category category) {
        if (category == null) return;
        if (this.categories.add(category)) {
            category.addProduct(this);
        }
    }

    public void removeCategory(Category category) {
        if (category == null) return;
        if (this.categories.remove(category)) {
            category.removeProduct(this);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
