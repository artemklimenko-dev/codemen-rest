package com.codemen.codemenrest.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Table;
import lombok.Data;
import jakarta.persistence.*;
import lombok.experimental.Accessors;

@Entity
@Data
@Accessors(chain = true)
@Table(name="companies")
public class Company {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @OneToOne
    @JoinColumn (name="company_id")
    @JsonBackReference
    private User user;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name = "catch_phrase")
    private String catchPhrase;

    @Column(name="bs")
    private String bs;
}
