//package com.joonseolee.musinsasearchassignment.config;
//
//import lombok.RequiredArgsConstructor;
//import org.hibernate.jpa.HibernatePersistenceProvider;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//
//@EnableJpaRepositories(
//        basePackages = "com.joonseolee.musinsasearchassignment",
//        entityManagerFactoryRef = "entityManagerFactoryBean",
//        transactionManagerRef = "jpaTransactionManager"
//)
//@Configuration
//@RequiredArgsConstructor
//public class DatasourceConfiguration {
//
//    private final DataSourceProperties dataSourceProperties;
//    private final JpaProperties jpaProperties;
//
//    @Bean
//    public DataSource dataSource() {
//        return DataSourceBuilder.create()
//                .driverClassName(dataSourceProperties.getDriverClassName())
//                .url(dataSourceProperties.getUrl())
//                .username(dataSourceProperties.getUsername())
//                .password(dataSourceProperties.getPassword())
//                .build();
//    }
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(DataSource dataSource) {
//        var entityFactory = new LocalContainerEntityManagerFactoryBean();
//        entityFactory.setDataSource(dataSource);
//        entityFactory.setPersistenceProviderClass(HibernatePersistenceProvider.class);
//        entityFactory.setPackagesToScan("com.joonseolee.musinsasearchassignment.entity");
//        entityFactory.setJpaPropertyMap(jpaProperties.getProperties());
//        return entityFactory;
//    }
//
//    @Bean
//    public PlatformTransactionManager jpaTransactionManager(EntityManagerFactory entityManagerFactory) {
//        var transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(entityManagerFactory);
//        return transactionManager;
//    }
//}
