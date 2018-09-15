package com.varguss.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.IOException;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class DatabaseConfig {
    @Bean
    public LocalSessionFactoryBean sessionFactory() throws IOException {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(getDatabaseDataSource());
        sessionFactoryBean.setPackagesToScan("com.varguss.domain");

        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL57Dialect");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.connection.useUnicode", "true");
        properties.setProperty("hibernate.connection.characterEncoding", "utf8");
        properties.setProperty("hibernate.connection.charSet", "utf8");

        sessionFactoryBean.setHibernateProperties(properties);

        return sessionFactoryBean;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }

    @Bean(name = "dataSource", destroyMethod = "close")
    public BasicDataSource getDatabaseDataSource() throws IOException {
        BasicDataSource databaseDataSource = new BasicDataSource();

        Properties properties = new Properties();

        ClassPathResource propertiesFileResource = new ClassPathResource("database.properties");
        properties.load(propertiesFileResource.getInputStream());

        databaseDataSource.setDriverClassName(properties.getProperty("driverClassName"));
        databaseDataSource.setUrl(properties.getProperty("url"));
        databaseDataSource.setUsername(properties.getProperty("username"));
        databaseDataSource.setPassword(properties.getProperty("password"));

        return databaseDataSource;
    }
}
