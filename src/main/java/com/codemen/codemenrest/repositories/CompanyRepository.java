package com.codemen.codemenrest.repositories;

import com.codemen.codemenrest.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}
