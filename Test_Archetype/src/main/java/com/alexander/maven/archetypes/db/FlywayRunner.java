package com.alexander.maven.archetypes.db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Properties;

import com.googlecode.flyway.core.Flyway;
import com.googlecode.flyway.core.util.ClassPathResource;

public class FlywayRunner {

	public static void main(String[] args){
		Flyway flyway = new Flyway();
		Properties props = getProperties();
		flyway.setDataSource(
				(String)props.get("db.test.url"), 
				(String)props.get("db.test.username"), 
				(String)props.get("db.test.password"));
		
		flyway.setLocations("com/alexander/maven/archetypes/db/migrations/");
		flyway.migrate();
	}
	
	public static Properties getProperties(){
		Properties p = new Properties();
		try {
			BufferedReader resource = new BufferedReader(new FileReader("src/main/filters/common-filter.properties"));
			
			p.load(resource);
		} catch (FileNotFoundException fnfe){
			fnfe.printStackTrace();
		} catch (IOException ioe){
			ioe.printStackTrace();
		}
		return p;
	}
}
