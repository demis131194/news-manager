package com.epam.lab.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import java.util.Properties;

@Configuration
@ComponentScan("com.epam.lab.repository")
@PropertySource("classpath:db/datasource.properties")
@EnableTransactionManagement
public class AppJpaRepositoryConfig {
    private static final String DATA_SOURCE_PROPERTY = "/db/datasource.properties";
    private static final String ENTITY_PATH = "com.epam.lab.model";                     // FIXME: 2/19/2020 Refactor

    @Bean(name = "data-source", destroyMethod = "close")
    public HikariDataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig(DATA_SOURCE_PROPERTY);
        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean(HikariDataSource dataSource) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setPackagesToScan(ENTITY_PATH);

        Properties properties = new Properties();
        properties.setProperty(AvailableSettings.FORMAT_SQL, "true");
        properties.setProperty(AvailableSettings.USE_SQL_COMMENTS, "true");
        properties.setProperty(AvailableSettings.DIALECT, "org.hibernate.dialect.PostgreSQL10Dialect");

        entityManagerFactoryBean.setJpaProperties(properties);
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();

        adapter.setShowSql(true);
        entityManagerFactoryBean.setJpaVendorAdapter(adapter);
        
        return entityManagerFactoryBean;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
