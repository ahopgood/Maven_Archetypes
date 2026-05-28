package com.alexander.maven.archetypes.dao.hibernate;

import com.alexander.maven.archetypes.dao.BaseDao;
import com.alexander.maven.archetypes.domain.TestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class HibernateTestConf {


    @Bean
    public BaseDao<TestEntity> hibernateDao(@Autowired LocalSessionFactoryBean sessionFactory) {
        TestHibernateDao dao = new TestHibernateDao();
//        dao.setSessionFactory(sessionFactory().getObject());
        dao.setSessionFactory(sessionFactory.getObject());
        System.out.println("++++++++++++++++ hibernate dao conf " +dao.hashCode());
        AbstractHibernateDao base = (AbstractHibernateDao<TestEntity>)dao;
        System.out.println("++++++++++++++++ hibernate dao conf " +base.hashCode());
        System.out.println("++++++++++++++++ session factory conf " +sessionFactory.hashCode());

        return dao;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.hsqldb.jdbcDriver");
        dataSource.setUrl("jdbc:hsqldb:mem:test;");
        dataSource.setUsername("sa");
        dataSource.setPassword("");

        return dataSource;
    }

//    @Bean
//    public LocalSessionFactoryBean sessionFactory(@Autowired DataSource dataSource, @Autowired @Qualifier("hibernateProperties") Properties hibernateProperties) {
//        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//        sessionFactory.setDataSource(dataSource);
//        sessionFactory.setPackagesToScan("com.alexander.maven.archetypes.domain");
//        sessionFactory.setHibernateProperties(hibernateProperties);
//
//        return sessionFactory;
//    }

//    @Bean
//    public HibernateTransactionManager hibernateTransactionManager(@Autowired LocalSessionFactoryBean sessionFactory) {
//        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
//        transactionManager.setSessionFactory(sessionFactory.getObject());
//        return transactionManager;
//    }

    @Bean(name = "hibernateProperties")
    public Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto","update");

        return hibernateProperties;
    }
}
