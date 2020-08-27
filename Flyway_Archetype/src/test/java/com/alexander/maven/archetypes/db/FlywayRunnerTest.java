package com.alexander.maven.archetypes.db;

import org.h2.jdbcx.JdbcDataSource;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import static org.junit.Assert.*;

public class FlywayRunnerTest {
    private static String DB_URL = "jdbc:h2:mem:target;DB_CLOSE_DELAY=6";
    private static String DB_USERNAME = "sa";
    private static String DB_PASSWORD = "sa";

    private String migrationsLocations = "com/alexander/maven/archetypes/db/migrations/";

    @Test
    public void testRunMigrations() throws SQLException {
        FlywayRunner flywayRunner = new FlywayRunner(migrationsLocations, getTestProperties());
        flywayRunner.runMigrations();

        //Get connection to the database and run some queries e.g. count(*) from person;
        DataSource dataSource = getDataSource();
        ResultSet results = dataSource
                .getConnection()
                .prepareStatement("SELECT COUNT(*) from PERSON;").executeQuery();
        results.next();
        System.out.println(results.toString());
        assertEquals(results.getInt(1), 2);
    }

    @Test (expected = NullPointerException.class)
    public void testRunMigrations_givenEmptyProperties() throws SQLException {
        FlywayRunner flywayRunner = new FlywayRunner(migrationsLocations, new Properties());
        flywayRunner.runMigrations();
    }

    Properties getTestProperties() {
        Properties properties = new Properties();
        properties.setProperty(FlywayRunner.DATABASE_URL_PROPERTY, DB_URL);
        properties.setProperty(FlywayRunner.USERNAME_PROPERTY, DB_USERNAME);
        properties.setProperty(FlywayRunner.PASSWORD_PROPERTY, DB_PASSWORD);
        return properties;
    }

    DataSource getDataSource() {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setUser(DB_USERNAME);
        dataSource.setPassword(DB_PASSWORD);
        dataSource.setURL(DB_URL);
        return dataSource;
    }
}