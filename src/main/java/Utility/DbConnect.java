package Utility;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnect {
	public static Connection ConnectToDB() throws Exception {
		// Registering the Driver
//	      DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		// Getting the connection
		Class.forName("com.mysql.jdbc.Driver");
		String mysqlUrl = "jdbc:mysql://localhost:3306/demo";
		Connection con = DriverManager.getConnection(mysqlUrl, "student", "student");
		System.out.println("Connection established......");
		return con;
	}
}
