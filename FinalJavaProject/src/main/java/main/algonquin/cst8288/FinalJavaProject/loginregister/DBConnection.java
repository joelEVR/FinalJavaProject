package main.algonquin.cst8288.FinalJavaProject.loginregister;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBConnection {
	
	private static Connection connection = null;

    private DBConnection() {}

	public static Connection getConnection() {
        if (connection == null) {
            synchronized (DBConnection.class) {
                if (connection == null) {
                    String url = "jdbc:mysql://localhost:3306/fwrp";
                    String username = "username";
                    String password = "username";
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        connection = DriverManager.getConnection(url, username, password);
                        System.out.println("Post establishing a DB connection - " + connection);
                    } catch (ClassNotFoundException | SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        
        return connection;
    }

    public static PreparedStatement getPreparedStatement(String sql) {
        PreparedStatement ps;
        Connection con = DBConnection.getConnection();
        try {
            ps = con.prepareStatement(sql);
            return ps;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}