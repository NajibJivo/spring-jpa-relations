package ek.osnb.jpa.orders.dto;

import java.util.Set;

public record ProductCreateRequest(
        String name,
        Double price,
        Set<Long> categoryIds
) {}
