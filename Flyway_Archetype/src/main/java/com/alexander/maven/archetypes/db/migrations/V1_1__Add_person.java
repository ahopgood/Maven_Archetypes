package com.alexander.maven.archetypes.db.migrations;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.googlecode.flyway.core.api.migration.jdbc.JdbcMigration;

public class V1_1__Add_person implements JdbcMigration {

	@Override
	public void migrate(Connection conn) throws Exception {
		PreparedStatement statement = 
				conn.prepareStatement("INSERT INTO person (ID, InsuranceNumber) VALUES (1,'JK168376A')");
		try {
			statement.execute();
		} finally {
			statement.close();
		}
	}

}
