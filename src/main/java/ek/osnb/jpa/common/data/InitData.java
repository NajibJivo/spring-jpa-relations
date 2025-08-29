package ek.osnb.jpa.common.data;

import ek.osnb.jpa.orders.model.Order;
import ek.osnb.jpa.orders.model.OrderLine;
import ek.osnb.jpa.orders.repository.OrderLineRepository;
import ek.osnb.jpa.orders.repository.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ek.osnb.jpa.orders.model.OrderStatus;


import java.time.LocalDate;
import java.util.List;


@Component
public class InitData implements CommandLineRunner {

    private final OrderRepository orderRepository;
    private final OrderLineRepository orderLineRepository;

    public InitData(OrderRepository orderRepository, OrderLineRepository orderLineRepository) {
        this.orderRepository = orderRepository;
        this.orderLineRepository = orderLineRepository;
    }
    @Override
    public void run(String... args) {
        Order order1 = new Order(LocalDate.now(), OrderStatus.PAID);
        Order order2 = new Order(LocalDate.now(), OrderStatus.PAID);
        orderRepository.saveAll(List.of(order1, order2));

        OrderLine ol1 = new OrderLine("Product A", 50.0, 2);
        OrderLine ol2 = new OrderLine("Product B", 30.0, 1);
        OrderLine ol3 = new OrderLine("Product C", 20.0, 5);
        OrderLine ol4 = new OrderLine("Product D", 15.0, 3);
        OrderLine ol5 = new OrderLine("Product E", 25.0, 4);

        ol1.setOrder(order1);
        ol2.setOrder(order1);
        ol3.setOrder(order2);
        ol4.setOrder(order2);
        ol5.setOrder(order2);

        orderLineRepository.saveAll(List.of(ol1, ol2, ol3, ol4, ol5));
    }
}
