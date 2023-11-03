package com.codemen.codemenrest.repositories;

import com.codemen.codemenrest.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
