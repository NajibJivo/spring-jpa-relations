package ek.osnb.jpa.orders.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import ek.osnb.jpa.common.model.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;




@Entity
public class Customer extends BaseEntity {
    private String name;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonManagedReference
    private Profile profile;


    public Customer() {}

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Profile getProfile() {
        return profile;
    }

    // lille helper metode så begge sider holdes i sync
    public void setProfile(Profile profile) {
        this.profile = profile;
        if(profile != null) profile.setCustomer(this);
    }
}
