package com.billing.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.billing.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
