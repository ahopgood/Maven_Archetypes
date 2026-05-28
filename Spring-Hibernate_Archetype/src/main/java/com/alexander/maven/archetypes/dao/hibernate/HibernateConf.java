package com.alexander.maven.archetypes.dao.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@PropertySource(value = "classpath:external.properties")
@Configuration
public class HibernateConf {


    @Autowired
    Environment env;
    // Add configuration for hibernate specific pieces
        // Datasource
        // session factory - can this be reused from the test config?
        // transaction manager
        // HibernatePersonDao?
    // Pull in properties

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        System.out.println("DB Driver " + env.getProperty("seed.db.driver"));
        dataSource.setDriverClassName(env.getProperty("seed.db.driver"));
        dataSource.setUrl(env.getProperty("seed.db.url"));
        dataSource.setUsername(env.getProperty("seed.db.username"));
        dataSource.setPassword(env.getProperty("seed.db.password"));
//        dataSource.ma
//				<property name="maxPoolSize" 				value="${seed.db.pool.maxpoolsize}" />
//				<property name="initialPoolSize" 			value="${seed.db.pool.initialpoolsize}" />
//				<property name="preferredTestQuery" 		value="select 1;" />
//				<property name="maxIdleTime" 				value="${seed.db.pool.maxidletime}" />
//				<property name="maxStatementsPerConnection"	value="10" />
        return dataSource;
    }


    @Bean(name = "hibernateProperties")
    public Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto","update");
        hibernateProperties.setProperty("hibernate.dialect","org.hibernate.dialect.MySQLDialect");

        return hibernateProperties;
    }

    @Bean
    public HibernatePersonDao hibernatePersonDao(@Autowired LocalSessionFactoryBean sessionFactory) {
        HibernatePersonDao dao = new HibernatePersonDao();
        dao.setSessionFactory(sessionFactory.getObject());
        return dao;
    }
}
