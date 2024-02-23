package com.example.MultipleDb.db1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = {
                "com.example.MultipleDb.db1.repo"             //package name of the repository
        },
        entityManagerFactoryRef = "db1EntityManagerFactory",
        transactionManagerRef = "db1TransactionManager"
)
public class Db1Config {

    //ek environment banaenge jo application.properies ko read kr sake hamare configuration
    @Autowired
    private Environment environment;

    //datasource -->
    @Bean
    @Primary
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(environment.getProperty("spring.datasource.url"));
        dataSource.setUsername(environment.getProperty("spring.datasource.username"));
        dataSource.setPassword(environment.getProperty("spring.datasource.password"));
        dataSource.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
//        We can put more configuration as per need
        return dataSource;
    }

    //entityManagerFactory
    @Bean(name = "db1EntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean db1EntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPackagesToScan("com.example.MultipleDb.db1.entities");  //konsa package scan krna hai entities ke liye
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());  //jo bhi jpa vendor adapter hai wo set kr denge
        //jo bhi hibernate ki properties set krni wo yha bta denge
        Map<String, String> properties = new HashMap<>();
//        properties.put("hibernate.dialect",environment.getProperty("spring.jpa.properties.hibernate.dialect"));
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.hbm2ddl.auto", "update");
        entityManagerFactoryBean.setJpaPropertyMap(properties); //hibernate ki properties yha set kr denge
        return entityManagerFactoryBean;
    }

    //platformTransactionManager
    @Primary
    @Bean(name = "db1TransactionManager")
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager manager = new JpaTransactionManager();
        manager.setEntityManagerFactory(db1EntityManagerFactory().getObject());
        return manager;
    }
}