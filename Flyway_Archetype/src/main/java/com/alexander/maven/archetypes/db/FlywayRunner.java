package com.alexander.maven.archetypes.db;

import java.util.Properties;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.Location;
import org.flywaydb.core.api.configuration.ClassicConfiguration;
import org.h2.jdbcx.JdbcDataSource;

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

		ClassicConfiguration conf = new ClassicConfiguration();
		conf.setDataSource(source);
		conf.setLocations(new Location(migrationLocation));
		Flyway flyway = new Flyway(conf);

		flyway.migrate();
	}
}
