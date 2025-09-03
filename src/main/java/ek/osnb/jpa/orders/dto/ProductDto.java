package ek.osnb.jpa.orders.dto;


import java.util.Set;

public record ProductDto(
        Long id,
        String name,
        double price,
        Set<CategoryDto> categories) {
}
