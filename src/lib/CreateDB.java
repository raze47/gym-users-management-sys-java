package lib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CreateDB {
	protected static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	protected static final String JDBC_URL = "jdbc:derby:database_sample1;create=true";

	public void createDB() throws ClassNotFoundException, SQLException{
	    Class.forName(DRIVER);
		Connection conn  =  DriverManager.getConnection(JDBC_URL);
		conn.createStatement().execute("create table channels(channel varchar(20), topic varchar(20), videoclip varchar(20))");
		
		conn.createStatement().execute("insert into channels values " + 
									 "('oodp', 'creational', 'singleton'), " +
									 "('oodp', 'creational', 'factory method'), "+
									 "('oodp', 'creational', 'abstract factory')");
		
		System.out.println("Database successfully created and connection established");
		
		
	}
}
