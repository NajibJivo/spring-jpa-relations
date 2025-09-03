package ek.osnb.jpa.orders.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import ek.osnb.jpa.common.model.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "order_line")
public class OrderLine extends BaseEntity {



    @ManyToOne
    private Product product;
    private int quantity;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;


    public OrderLine() {}

    public OrderLine(Product product, double unitPrice, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }


    public Product getProducts () {
        return product;
    }

    public void setProducts (Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Order getOrder() {return order;}

    public void setOrder(Order order) {this.order = order;}
}
