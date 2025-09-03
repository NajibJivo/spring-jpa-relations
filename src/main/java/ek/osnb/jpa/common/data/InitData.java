package ek.osnb.jpa.common.data;

import ek.osnb.jpa.orders.model.*;
import ek.osnb.jpa.orders.repository.CategoryRepository;
import ek.osnb.jpa.orders.repository.OrderRepository;

import ek.osnb.jpa.orders.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.util.List;

@Transactional
@Component
public class InitData implements CommandLineRunner {

    private final OrderRepository orderRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;


    public InitData(OrderRepository orderRepository,  CategoryRepository categoryRepository,
                    ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }
    @Override
    public void run(String... args) throws Exception {
        Order order1 = new Order(LocalDate.now(), OrderStatus.PAID);
        Order order2 = new Order(LocalDate.now(), OrderStatus.PAID);

        // 1) Opret order lines (trin 1)
      OrderLine orderLine1 = new OrderLine();
      OrderLine orderLine2 = new OrderLine();
      OrderLine orderLine3 = new OrderLine();
      OrderLine orderLine4 = new OrderLine();
      OrderLine orderLine5 = new OrderLine();

        // 2) Tilføj via helper-metoden (trin 2) – holder begge sider i sync
        order1.addOrderLine(orderLine1);
        order1.addOrderLine(orderLine2);

        order2.addOrderLine(orderLine3);
        order2.addOrderLine(orderLine4);
        order2.addOrderLine(orderLine5);

        // Gem kun parent – børn følger med pga. cascade = ALL
        orderRepository.saveAll(List.of(order1, order2));

        // Category Objects
        Category electronics = new Category();
        electronics.setName("Electronics");

        Category books = new Category();
        books.setName("Books");


        // save the categories
        categoryRepository.saveAll(List.of(electronics, books));


        // Product Objects
        Product novel = new Product();
        novel.setName("Novel");
        novel.setPrice(120);

        Product phone = new Product();
        phone.setName("SmartPhone");
        phone.setPrice(1400);

        novel.addCategory(books);
        phone.addCategory(electronics);


        // save the products
        productRepository.saveAll(List.of(novel, phone));
    }
}
