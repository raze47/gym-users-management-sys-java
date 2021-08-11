package lib;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;


public class QueryDB { 
	public static final String SQL_STATEMENT = " select * from channels";
	public static void main(String args[]) throws ClassNotFoundException, SQLException {
		Connection conn = DriverManager.getConnection("jdbc:derby:GymDatabase;create=true");
		Statement statement = conn.createStatement();
		ResultSet resultSet = statement.executeQuery(SQL_STATEMENT);
		ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
		int columnCount = resultSetMetaData.getColumnCount();
		for (int i = 1; i <= columnCount; i++){
			System.out.format("%20s", resultSetMetaData.getColumnName(i)+ " | ");
		}
		while (resultSet.next()) {
			System.out.println("");
			for (int i = 1; i <= columnCount; i++) {
				
				System.out.format("%20s", resultSet.getString(i)+ " | ");
			}
		}
		if(statement!=null)
			statement.close();
		if(conn!=null)
			conn.close();
	}
}