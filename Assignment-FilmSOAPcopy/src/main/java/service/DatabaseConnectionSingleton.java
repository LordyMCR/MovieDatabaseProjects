package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * <h1>DatabaseConnectionSingleton class</h1>
 * <p>The singleton class ensures a single point of access to a database connection, using the Singleton design pattern
 * and the MySQL JDBC driver. It provides methods to close the connection and create a prepared statement, and has private
 * fields for the connection, user, password, and url of the database.</p>
 */
public class DatabaseConnectionSingleton {
	/** Private DatabaseConnectionSingleton instance to establish connection to the database. */
	private static DatabaseConnectionSingleton instance;
	/** Private Connection conn holds the connection to the database. */
	private Connection conn;
	/** Private String user holds the username for the database connection. */
	private String user = "daniellord";
	/** Private String password holds the password for the database connection. */
    private String password = "password123";
    /** Private String url holds the address for the database connection. */
    private String url = "jdbc:mysql://universitydatabase.com:6301/"+user;
    /**
	 * Private constructor that establishes a connection to the database using the MySQL JDBC driver. Keeping constructor private
	 * restricts DatabaseConnectionSingleton objects to be made. If a ClassNotFoundException or SQLException occurs, it will be
	 * caught and printed to the console.
	 */
	private DatabaseConnectionSingleton() {
		try {
	    	Class.forName("com.mysql.jdbc.Driver");
	    	conn = DriverManager.getConnection(url, user, password);
	    } catch (ClassNotFoundException | SQLException e) {
	    	e.printStackTrace();
	    }
	}
	/**
	 * Public getInstance method that establishes a connection to the database using the MySQL JDBC driver. This method ensures
	 * that only one instance of the class is created throughout the application's lifecycle.
	 * @return the single instance of the DatabaseConnectionSingleton class
	 */
	public static synchronized DatabaseConnectionSingleton getInstance() {
		if (instance == null) {
			return new DatabaseConnectionSingleton();
		}
		return instance;
	}
	/** Public method to close the connection to the database */
	public void closeConnection() {
		
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Public method creates a prepared statement for the provided SQL using the current connection
	 * @param String sql
	 * @return Prepared statement for the provided SQL
	 * @throws SQLException
	 */
	public PreparedStatement createPreparedStatement(String sql) throws SQLException {
		return conn.prepareStatement(sql);
	}
}