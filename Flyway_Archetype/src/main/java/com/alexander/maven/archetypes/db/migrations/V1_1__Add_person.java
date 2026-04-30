package com.alexander.maven.archetypes.db.migrations;

import java.sql.PreparedStatement;
import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

public class V1_1__Add_person extends BaseJavaMigration {

	@Override
	public void migrate(Context context) throws Exception {
		PreparedStatement statement =
				context.getConnection().prepareStatement("INSERT INTO person (ID, InsuranceNumber) VALUES (1,'JK168376A')");
		try {
			statement.execute();
		} finally {
			statement.close();
		}
	}
}
