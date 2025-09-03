package ek.osnb.jpa.orders.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import ek.osnb.jpa.common.model.BaseEntity;

import jakarta.persistence.*;


@Entity
@Table(name = "order_line")
public class OrderLine extends BaseEntity {



    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    private int quantity;


    @JsonBackReference("order-lines")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;


    public OrderLine() {}

    public OrderLine(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }


    public Product getProduct () {
        return product;
    }

    public void setProduct (Product product) {
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
