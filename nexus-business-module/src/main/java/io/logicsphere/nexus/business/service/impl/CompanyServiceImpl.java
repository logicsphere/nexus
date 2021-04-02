package io.logicsphere.nexus.business.service.impl;

import io.logicsphere.nexus.business.entity.Company;
import io.logicsphere.nexus.business.repository.CompanyRepository;
import io.logicsphere.nexus.business.service.CompanyService;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(final CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public void createOrUpdate(Company company) {
        company.getPhones().forEach(p -> p.setCompany(company));
        companyRepository.save(company);
    }

}
