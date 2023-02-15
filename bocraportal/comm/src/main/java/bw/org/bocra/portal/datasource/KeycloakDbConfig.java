package bw.org.bocra.portal.datasource;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    entityManagerFactoryRef = "keycloakEntityManagerFactory",
    transactionManagerRef = "keycloakTransactionManager",
    basePackages = {
        "bw.org.bocra.portal.keycloak"
    }
)
public class KeycloakDbConfig {

    @Bean(name = "keycloakDataSource")
    @ConfigurationProperties(prefix = "keycloak.datasource")
    public DataSource customerDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "keycloakEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean
    entityManagerFactory(
        EntityManagerFactoryBuilder builder,
        @Qualifier("keycloakDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("bw.org.bocra.portal.keycloak")
                .persistenceUnit("keycloakdb")
                .build();
    }

    @Bean(name = "keycloakTransactionManager")
    public PlatformTransactionManager customerTransactionManager(
        @Qualifier("keycloakEntityManagerFactory") EntityManagerFactory customerEntityManagerFactory ) {
        return new JpaTransactionManager(customerEntityManagerFactory);
    }
}