package io.logicsphere.nexus.business.service;

import io.logicsphere.nexus.business.BusinessModuleAutoConfiguration;
import io.logicsphere.nexus.business.entity.Company;
import io.logicsphere.nexus.business.entity.CompanyPhone;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {BusinessModuleAutoConfiguration.class})
class CompanyServiceTest {

    @Autowired
    CompanyService companyService;

    @Test
    void testCompanySave() {
        final var company = new Company()
                .setName("Custom Company")
                .setAddressLine1("Custom address line 1");

        company.getPhones()
                .add(new CompanyPhone()
                        .setCountryCode("351")
                        .setPhoneNumber("000000000")
                        .setLabel("Test Phone Company")
                );

        companyService.createOrUpdate(company);
        System.out.println(company);
        assertThat(company.getCreatedAt()).isNotNull();
        assertThat(company.getPhones().get(0).getId()).isNotNull();
    }

}
