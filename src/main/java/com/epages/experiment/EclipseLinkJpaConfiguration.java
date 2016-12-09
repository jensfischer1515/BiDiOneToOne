package com.epages.experiment;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.jta.JtaTransactionManager;

import java.util.Map;

import javax.sql.DataSource;

import static com.google.common.collect.Maps.newHashMap;
import static org.eclipse.persistence.config.PersistenceUnitProperties.DDL_DATABASE_GENERATION;
import static org.eclipse.persistence.config.PersistenceUnitProperties.DDL_GENERATION_MODE;
import static org.eclipse.persistence.config.PersistenceUnitProperties.SESSION_CUSTOMIZER;
import static org.eclipse.persistence.config.PersistenceUnitProperties.WEAVING;

@Configuration
@EntityScan
@EnableJpaRepositories
public class EclipseLinkJpaConfiguration extends JpaBaseConfiguration {

    public EclipseLinkJpaConfiguration(DataSource dataSource,
                                       JpaProperties jpaProperties,
                                       ObjectProvider<JtaTransactionManager> jtaTransactionManagerProvider) {
        super(dataSource, jpaProperties, jtaTransactionManagerProvider);
    }

    @Override
    protected AbstractJpaVendorAdapter createJpaVendorAdapter() {
        return new EclipseLinkJpaVendorAdapter();
    }

    @Override
    protected Map<String, Object> getVendorProperties() {
        Map<String, Object> vendorProperties = newHashMap(getProperties().getProperties());
        vendorProperties.put(WEAVING, "false");
        vendorProperties.put(DDL_GENERATION_MODE, DDL_DATABASE_GENERATION);
        vendorProperties.put(SESSION_CUSTOMIZER, UUIDSequence.class.getName());
        return vendorProperties;
    }
}
