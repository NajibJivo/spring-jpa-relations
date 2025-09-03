package ek.osnb.jpa.orders.dto;

import ek.osnb.jpa.orders.model.Category;
import ek.osnb.jpa.orders.model.Product;

import java.util.LinkedHashSet;
import java.util.stream.Collectors;

public class DtoMapper {

    private DtoMapper(){}

    public static CategoryDto toDto(Category c){
        return new CategoryDto(c.getId(),c.getName());
    }

    // LinkedHashSet for deterministisk rækkefølge, bevarer indsætningsrækkefølgen og undgår dubletter.
    public static ProductDto toDto(Product p){
        var catDtos = p.getCategories().stream()
                .map(DtoMapper::toDto)
                .collect(Collectors.toCollection(LinkedHashSet::new));
        return new ProductDto(p.getId(), p.getName(), p.getPrice(), catDtos);
    }
}
