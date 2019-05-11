package com.boot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactoryActiviti",
        transactionManagerRef = "transactionManagerActiviti",
        basePackages = {"com.boot.dao.activiti"}) //设置Repository所在位置
public class ActivitiDBConfig {

    @Autowired
    @Qualifier("activitiDataSource")
    private DataSource activitiDataSource;

    @Bean(name = "entityManagerActiviti")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryActiviti(builder).getObject().createEntityManager();
    }

    @Bean(name = "entityManagerFactoryActiviti")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryActiviti(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(activitiDataSource)
                .properties(getVendorProperties())
                .packages("com.boot.pojo.activiti") //设置实体类所在位置
                .persistenceUnit("primaryPersistenceUnit")
                .build();
    }

    @Autowired
    private JpaProperties jpaProperties;

    private Map<String, String> getVendorProperties() {
        return jpaProperties.getProperties();
    }

    @Bean(name = "transactionManagerActiviti")
    public PlatformTransactionManager transactionManagerPrimary(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryActiviti(builder).getObject());
    }

}
