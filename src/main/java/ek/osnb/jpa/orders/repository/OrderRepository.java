package ek.osnb.jpa.orders.repository;

import ek.osnb.jpa.orders.model.Order;
import ek.osnb.jpa.orders.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByStatus(OrderStatus status);
}
