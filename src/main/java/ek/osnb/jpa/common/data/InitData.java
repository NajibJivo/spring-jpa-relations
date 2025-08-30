package ek.osnb.jpa.common.data;

import ek.osnb.jpa.orders.model.Order;
import ek.osnb.jpa.orders.model.OrderLine;
import ek.osnb.jpa.orders.repository.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ek.osnb.jpa.orders.model.OrderStatus;


import java.time.LocalDate;
import java.util.List;


@Component
public class InitData implements CommandLineRunner {

    private final OrderRepository orderRepository;


    public InitData(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    @Override
    public void run(String... args) throws Exception {
        Order order1 = new Order(LocalDate.now(), OrderStatus.PAID);
        Order order2 = new Order(LocalDate.now(), OrderStatus.PAID);

        // 1) Opret order lines (trin 1)
      OrderLine orderLine1 = new OrderLine("Product A", 50.0,2);
      OrderLine orderLine2 = new OrderLine("Product B", 30.0,1);
      OrderLine orderLine3 = new OrderLine("Product C", 20.0,5);
      OrderLine orderLine4 = new OrderLine("Product D", 15.0,3);
      OrderLine orderLine5 = new OrderLine("Product E", 25.0,4);

        // 2) Tilføj via helper-metoden (trin 2) – holder begge sider i sync
        order1.addOrderLine(orderLine1);
        order1.addOrderLine(orderLine2);

        order2.addOrderLine(orderLine3);
        order2.addOrderLine(orderLine4);
        order2.addOrderLine(orderLine5);

        // Gem kun parent – børn følger med pga. cascade = ALL
        orderRepository.saveAll(List.of(order1, order2));
    }
}
