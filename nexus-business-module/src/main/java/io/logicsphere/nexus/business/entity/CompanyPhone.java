package io.logicsphere.nexus.business.entity;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.time.ZonedDateTime;

/**
 * Company phone entity model
 * @version 1.0.0
 * @author Gabriel Ribeiro
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "company_phone")
public class CompanyPhone {

    /**
     * Phone ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "snowflake")
    @Column(name = "phone_id")
    private Long id;

    /**
     * Phone label
     */
    @Column(name = "phone_label")
    private String label;

    /**
     * Phone country code
     */
    @Column(name = "phone_country_code")
    private String countryCode;

    /**
     * Phone number
     */
    @Column(name = "phone_number")
    private String phoneNumber;

    /**
     * Phone creation date
     */
    @Column(name = "created_at")
    @Generated(GenerationTime.INSERT)
    private ZonedDateTime createdAt;

    /**
     * Phone last update date
     */
    @Column(name = "last_update")
    @Generated(GenerationTime.ALWAYS)
    private ZonedDateTime lastUpdate;

    /**
     * Phone status
     */
    @Column(name = "enabled")
    @Generated(GenerationTime.ALWAYS)
    private boolean enabled;

    /**
     * Referenced company
     */
    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "company_id")
    @ToString.Exclude
    private Company company;

}
