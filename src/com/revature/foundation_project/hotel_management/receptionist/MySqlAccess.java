package com.revature.foundation_project.hotel_management.receptionist;
import java.sql.*;

public class MySqlAccess {
	private static Connection connection=null;
	private static Statement statement=null;
	private PreparedStatement preparedStatement=null;
	private ResultSet resultSet=null;
	static {
		try {
		connection=DriverManager.getConnection("jdbc:mysql://localhost/hotel_management","root","Az@770211");
		statement=connection.createStatement();
		}catch(Exception e) {}
		}
	  public static Connection getConnection()
	    {
	        return connection;
	    }
	  public static Statement getStatement() {
		  return statement;
	  }

}

