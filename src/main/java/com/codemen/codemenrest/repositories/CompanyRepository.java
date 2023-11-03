package com.codemen.codemenrest.repository;

import com.codemen.codemenrest.entity.Company;
import com.codemen.codemenrest.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}
