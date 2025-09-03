package ek.osnb.jpa.orders.service;

import ek.osnb.jpa.orders.model.Category;
import ek.osnb.jpa.orders.model.Product;
import ek.osnb.jpa.orders.repository.CategoryRepository;
import ek.osnb.jpa.orders.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    // Constructor injection
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional(readOnly=true)
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Transactional(readOnly=true)
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("product not found: " + id));
    }

    @Transactional
    public Product createProduct(Product product) {
        product.setId(null);
        return productRepository.save(product);
    }

    @Transactional
    public Product updateProduct(Long id, Product inbound) {
        Product existing = findById(id);
        existing.setName(inbound.getName());
        existing.setPrice(inbound.getPrice());

        // 1) Saml kategori-IDs fra inbound
        var ids = inbound.getCategories().stream()
                .map(Category::getId)
                .filter(Objects::nonNull)
                .collect(Collectors.toCollection(LinkedHashSet::new));

        // 2) Hent kategorier og tjek mismatch
        var found = new HashSet<>(categoryRepository.findAllById(ids));
        if(found.size() != ids.size()) throw new RuntimeException("One more categories not found");

        // 3) Ryd korrekt via helper-metoder (bevar sync på begge sider)
        new HashSet<>(existing.getCategories()).forEach(c -> existing.removeCategory(c));
        // tilføj nye
        found.forEach(existing::addCategory);

        return productRepository.save(existing);

    }

    @Transactional
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}