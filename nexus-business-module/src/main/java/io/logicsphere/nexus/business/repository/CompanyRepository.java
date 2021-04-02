package io.logicsphere.nexus.business.repository;

import io.logicsphere.nexus.business.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CompanyRepository extends JpaRepository<Company, Long>,
                                           PagingAndSortingRepository<Company, Long> {
}
