package ek.osnb.jpa.orders.controller;

import ek.osnb.jpa.orders.model.Product;
import ek.osnb.jpa.orders.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Product product = productService.findById(id);
        try {
            return ResponseEntity.ok(product);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Product> save(@RequestBody Product product) {
        Product saveProduct = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveProduct);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }
}
