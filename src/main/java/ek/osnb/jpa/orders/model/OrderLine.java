package ek.osnb.jpa.orders.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import ek.osnb.jpa.common.model.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_line")
public class OrderLine extends BaseEntity {

    private String product;
    private double unitPrice;
    private int quantity;

    @JsonBackReference
    @ManyToOne(optional = false)
    @JoinColumn(name = "order_id")
    private Order order;


    public OrderLine() {}

    public OrderLine(String product, double unitPrice, int quantity) {
        this.product = product;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
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
