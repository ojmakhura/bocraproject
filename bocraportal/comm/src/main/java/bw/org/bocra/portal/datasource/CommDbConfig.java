package bw.org.bocra.portal.datasource;

import java.util.HashMap;

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

import com.zaxxer.hikari.HikariConfig;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "commEntityManagerFactory", 
    transactionManagerRef = "commTransactionManager", 
    basePackages = {
        "bw.org.bocra.portal.message"
    }
)
public class CommDbConfig {

    @Primary
    @Bean(name = "commDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource commDataSource() {
        HikariConfig config = new HikariConfig();
        return DataSourceBuilder.create()
                .build();
    }

    @Primary
    @Bean(name = "commEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean commEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("commDataSource") DataSource dataSource) {

        LocalContainerEntityManagerFactoryBean em = builder.dataSource(dataSource)
                .packages("bw.org.bocra.portal.message")
                .persistenceUnit("commdb")
                .build();

        // HashMap<String, Object> properties = new HashMap<>();
        // properties.put("hibernate.hbm2ddl.auto", "update");
        // properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        // em.setJpaPropertyMap(properties);
        return em;
    }

    @Primary
    @Bean(name = "commTransactionManager")
    public PlatformTransactionManager commTransactionManager(
            @Qualifier("commEntityManagerFactory") EntityManagerFactory commEntityManagerFactory) {
        return new JpaTransactionManager(commEntityManagerFactory);
    }
}
