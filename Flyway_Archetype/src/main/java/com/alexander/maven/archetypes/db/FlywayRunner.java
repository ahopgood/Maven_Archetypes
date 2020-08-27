package com.alexander.maven.archetypes.db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.googlecode.flyway.core.Flyway;
import com.googlecode.flyway.core.util.ClassPathResource;
import org.h2.jdbcx.JdbcDataSource;
import org.h2.tools.Server;

public class FlywayRunner {

	protected static final String USERNAME_PROPERTY = "db.test.username";
	protected static final String PASSWORD_PROPERTY = "db.test.password";
	protected static final String DATABASE_URL_PROPERTY = "db.test.url";

	Properties properties;
	String migrationLocation;

	public FlywayRunner(String migrationLocation, Properties databaseConnectionProperties) {
		this.properties = databaseConnectionProperties;
		this.migrationLocation = migrationLocation;
	}

	public void runMigrations() {
		JdbcDataSource source = new JdbcDataSource();
		source.setPassword((String)properties.get(PASSWORD_PROPERTY));
		source.setURL((String)properties.get(DATABASE_URL_PROPERTY));
		source.setUser((String)properties.get(USERNAME_PROPERTY));

		Flyway flyway = new Flyway();
		flyway.setDataSource(source);
		flyway.setLocations(migrationLocation);
		flyway.migrate();
	}
}
