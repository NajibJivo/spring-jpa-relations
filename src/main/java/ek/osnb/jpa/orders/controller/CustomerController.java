package ek.osnb.jpa.orders.controller;

import ek.osnb.jpa.orders.model.Customer;
import ek.osnb.jpa.orders.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

        private final CustomerService service;
        public CustomerController(CustomerService service) { this.service = service; }

        @GetMapping
        public ResponseEntity<List<Customer>> getAll() { return ResponseEntity.ok(service.findAll()); }

        @GetMapping("/{id}")
        public ResponseEntity<Customer> getById(@PathVariable Long id) { return ResponseEntity.ok(service.findById(id)); }

        @PostMapping
        public ResponseEntity<Customer> create(@RequestBody Customer c) {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.create(c));
        }

        @PutMapping("/{id}")
        public ResponseEntity<Customer> update(@PathVariable Long id, @RequestBody Customer incoming) {
            return ResponseEntity.ok(service.update(id, incoming));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> delete(@PathVariable Long id) {
            service.delete(id);
            return ResponseEntity.noContent().build();
        }
}
