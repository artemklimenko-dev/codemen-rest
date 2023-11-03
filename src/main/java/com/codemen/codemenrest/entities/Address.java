package com.codemen.codemenrest.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;


@Entity
@Data
@Accessors(chain = true)
@Table(name="addresses")
public class Address {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @OneToOne
    @JoinColumn (name="address_id")
    @JsonBackReference
    private User user;

    @Column(name="street", nullable = false)
    private String street;

    @Column(name = "suite")
    private String suite;

    @Column(name="city")
    private String city;

    @Column(name="zipcode")
    private String zipcode;
}
