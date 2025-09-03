package ek.osnb.jpa.orders.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import ek.osnb.jpa.common.model.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Profile extends BaseEntity {
    private String bio;

    @OneToOne
    @JoinColumn(name = "customer_id", unique = true) // nullable hvis profil er valgfri
    @JsonManagedReference
    private Customer customer;

    public Profile() {}

    public Profile(String bio) {
        this.bio = bio;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
