package ek.osnb.jpa.orders.service;

import ek.osnb.jpa.orders.model.Customer;
import ek.osnb.jpa.orders.model.Profile;
import ek.osnb.jpa.orders.repository.CustomerRepository;
import ek.osnb.jpa.orders.repository.ProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;


    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional(readOnly = true)
    public List<Customer> findAll(){
        return customerRepository.findAll();
    }

    public Customer findById(Long id){
      return customerRepository.findById(id)
              .orElseThrow(() -> new RuntimeException("Customer not found " + id));
    }

    public Customer create(Customer c) {
        // hold 1-1 i sync
        if (c.getProfile() != null) c.getProfile().setCustomer(c);
        return customerRepository.save(c);
    }


    // --- UPDATE ---
    public Customer update(Long id, Customer incoming) {
        Customer c = findById(id);

        // navn (kun hvis sendt)
        if (incoming.getName() != null) c.setName(incoming.getName());

        // profil (opret/ændr hvis sendt)
        if (incoming.getProfile() != null) {
            if (c.getProfile() == null) {
                c.setProfile(new Profile()); // sætter også customer på profilen
            }
            // kopier simple felter
            c.getProfile().setBio(incoming.getProfile().getBio());
        }

        // valgfrit: hvis du vil KUNNE fjerne profilen helt ved at sende "profile": null
        // else if (incoming.getProfile() == null) {
        //     c.setProfile(null); // orphanRemoval sletter profilen i DB
        // }

        return c; // managed; flushes ved commit
    }

    // --- DELETE ---
    public void delete(Long id) {
        Customer c = findById(id);
        customerRepository.delete(c); // orphanRemoval fjerner Profile
    }
}
