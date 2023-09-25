package com.ftn.railwayapp.model.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "address")
@Getter
@Setter
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String streetNumber;

    @Column(nullable = false)
    private String zipcode;

    public Address(String city, String street, String streetNumber, String zipcode) {
        this.city = city;
        this.zipcode = zipcode;
        this.street = street;
        this.streetNumber = streetNumber;
    }
}
