package com.codemen.codemenrest.repositories;

import com.codemen.codemenrest.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUsername(String username);
    Page<User> findAll(Pageable page);

    Page<User> findByNameContaining(String username, Pageable page);
}
