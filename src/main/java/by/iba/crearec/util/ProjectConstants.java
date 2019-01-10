package by.iba.crearec.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ProjectConstants {
	public static final String DATABASE_NAME = "postgres";
	public static final String DATABASE_USER = "postgres";
	public static final String DATABASE_PASSWORD = "postgres";
	public static final String DATABASE_URL = "jdbc:postgresql://postgres:5432/" + DATABASE_NAME;
}
