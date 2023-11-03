package com.codemen.codemenrest.repository;

import com.codemen.codemenrest.entity.Address;
import com.codemen.codemenrest.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
