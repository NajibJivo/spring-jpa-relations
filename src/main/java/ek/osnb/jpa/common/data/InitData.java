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
    @Transactional
    public void run(String... args) throws Exception {
        // 1) Opret kategorier
        Category electronics = new Category(); electronics.setName("Electronics");
        Category books = new Category(); books.setName("Books");
        categoryRepository.saveAll(List.of(electronics, books));


        // 2) Opret produkter og link til kategorier (ejersiden = Product)
        Product novel =  new Product(); novel.setName("Novel"); novel.setPrice(120);
        Product phone =  new Product(); phone.setName("SmartPhone");  phone.setPrice(1400);
        novel.addCategory(books);
        phone.addCategory(electronics);
        productRepository.saveAll(List.of(novel, phone));

        // 3) Opret ordrer
        Order order1 = new Order(LocalDate.now(), OrderStatus.PAID);
        Order order2 = new Order(LocalDate.now(), OrderStatus.PAID);

        // 4) Opret order lines
        OrderLine orderLine1 = new OrderLine();
        OrderLine orderLine2 = new OrderLine();
        OrderLine orderLine3 = new OrderLine();
        OrderLine orderLine4 = new OrderLine();
        OrderLine orderLine5 = new OrderLine();

        // >>> Sæt produkt + antal HER, før det add'es til ordre <<<
        orderLine1.setProduct(novel); orderLine1.setQuantity(2);
        orderLine2.setProduct(phone); orderLine2.setQuantity(1);
        orderLine3.setProduct(novel); orderLine3.setQuantity(5);
        orderLine4.setProduct(phone); orderLine4.setQuantity(3);
        orderLine5.setProduct(novel); orderLine5.setQuantity(1);

        // 5) Link lines til ordrer via helper-metoden (holder begge sider i sync)
        order1.addOrderLine(orderLine1);
        order1.addOrderLine(orderLine2);
        order2.addOrderLine(orderLine3);
        order2.addOrderLine(orderLine4);
        order2.addOrderLine(orderLine5);

        // 6) Gem ordrer (cascade = ALL på Order → OrderLine)
        orderRepository.saveAll(List.of(order1, order2));
    }
}
