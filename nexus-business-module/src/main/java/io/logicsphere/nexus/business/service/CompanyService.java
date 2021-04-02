package io.logicsphere.nexus.business.service;

import io.logicsphere.nexus.business.entity.Company;

/**
 * Handle company registries
 * @author Gabriel Ribeiro
 * @version 1.0.0
 */
public interface CompanyService {
    void createOrUpdate(Company company);
}
