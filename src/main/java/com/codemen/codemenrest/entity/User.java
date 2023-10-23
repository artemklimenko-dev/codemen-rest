package com.codemen.codemenrest.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.hateoas.RepresentationModel;


@Entity
@Data
@Table(name="users")
@Accessors(chain = true)
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name="email", nullable = false, unique = true)
    private String email;

    @Column(name="phone")
    private String phone;
    
    @Column(name="website")
    private String website;
    @Override
    public String toString() {
        return String.format(
                "User[id=%d, firstName='%s']",
                id, username);
    }
}