package ek.osnb.jpa.orders.dto;


import java.util.Set;

public record ProductUpdateRequest(
        String name,
        Double price,
        Set<Long> categoryIds) {
}
