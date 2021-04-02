package io.logicsphere.nexus.business.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Company entity model
 * @version 1.0.0
 * @author Gabriel Ribeiro
 */
@Data
@Accessors(chain = true)

@Entity
@Table(name = "company")
@GenericGenerator(
    name = "snowflake",
    strategy = "io.logicsphere.nexus.business.hibernate.SnowflakeSequenceGenerator"
)
public class Company {

    /**
     * Company ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "snowflake")
    @Column(name = "company_id")
    private Long id;

    /**
     * Company Name
     */
    @Column(name = "company_name")
    private String name;

    /**
     * Company Address Line 1
     */
    @Column(name = "company_address_1")
    private String addressLine1;

    /**
     * Company Address Line 2
     */
    @Column(name = "company_address_2")
    private String addressLine2;

    /**
     * Company Address Line 3
     */
    @Column(name = "company_address_3")
    private String addressLine3;

    /**
     * Company City
     */
    @Column(name = "company_city")
    private String city;

    /**
     * Company Region
     */
    @Column(name = "company_region")
    private String region;

    /**
     * Company Zip Code
     */
    @Column(name = "company_zip_code")
    private String zipCode;

    /**
     * Company Country
     */
    @Column(name = "company_country")
    private String country;

    /**
     * Company creation date
     */
    @Column(name = "created_at")
    @Generated(GenerationTime.INSERT)
    private ZonedDateTime createdAt;

    /**
     * Company last update date
     */
    @Column(name = "last_update")
    @Generated(GenerationTime.ALWAYS)
    private ZonedDateTime lastUpdate;

    /**
     * Company status
     */
    @Column(name = "enabled")
    @Generated(GenerationTime.ALWAYS)
    private boolean enabled;

    @OneToMany(mappedBy = "company", cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    private List<CompanyPhone> phones = new ArrayList<>();

}
