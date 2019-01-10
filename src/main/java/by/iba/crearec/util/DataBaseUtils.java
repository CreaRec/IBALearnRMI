package by.iba.crearec.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(DataBaseUtils.class);

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("org.postgresql.Driver");
		return DriverManager.getConnection(ProjectConstants.DATABASE_URL, ProjectConstants.DATABASE_USER, ProjectConstants.DATABASE_PASSWORD);
	}

	public static void closeConnection(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			LOGGER.error("", e);
		}
	}
}
