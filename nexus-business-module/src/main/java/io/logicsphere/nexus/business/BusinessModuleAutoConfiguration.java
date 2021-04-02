package io.logicsphere.nexus.business;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.logicsphere.nexus.business.hibernate.SnakecasePhysicalNamingStrategy;
import io.logicsphere.nexus.business.hibernate.SnowflakeSequenceGenerator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.AbstractEntityManagerFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import xyz.downgoon.snowflake.Snowflake;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import javax.transaction.TransactionManager;

@Profile("!test")
@Configuration
@ComponentScan("io.logicsphere.nexus.business")
@EnableJpaRepositories
@EnableConfigurationProperties({DataSourceProperties.class, JpaProperties.class})
public class BusinessModuleAutoConfiguration {

    private final DataSourceProperties dataSourceProperties;
    private final JpaProperties jpaProperties;

    public BusinessModuleAutoConfiguration(final DataSourceProperties dataSourceProperties,
                                           final JpaProperties jpaProperties) {
        this.dataSourceProperties = dataSourceProperties;
        this.jpaProperties = jpaProperties;
    }

    @PostConstruct
    public void setupSnowflake() {
        final var snowflake = new Snowflake(1, 1);
        SnowflakeSequenceGenerator.setSnowflake(snowflake);
    }

    @Bean
    @ConditionalOnMissingBean(DataSource.class)
    public DataSource dataSource() {
        final var config = new HikariConfig();
        config.setJdbcUrl(dataSourceProperties.getUrl());
        config.setUsername(dataSourceProperties.getUsername());
        config.setPassword(dataSourceProperties.getPassword());
        return new HikariDataSource(config);
    }

    @Bean
    @ConditionalOnMissingBean(AbstractEntityManagerFactoryBean.class)
    public AbstractEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        final var adapter = new HibernateJpaVendorAdapter();
        adapter.setGenerateDdl(false);

        adapter.getJpaPropertyMap()
                .put("hibernate.physical_naming_strategy", SnakecasePhysicalNamingStrategy.class.getCanonicalName());

        adapter.getJpaPropertyMap().putAll(jpaProperties.getProperties());

        final var factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(adapter);
        factory.setPackagesToScan("io.logicsphere.nexus.business.entity");
        factory.setDataSource(dataSource);
        return factory;
    }

    @Bean
    @ConditionalOnMissingBean(TransactionManager.class)
    public PlatformTransactionManager transactionManager(EntityManagerFactory factory) {
        final var txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(factory);
        return txManager;
    }

}
