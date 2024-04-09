package main.algonquin.cst8288.FinalJavaProject.loginregister;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//public class DBConnection {
//	
//	private static Connection connection = null;
//
//    // Constructor privado para prevenir instanciación
//    private DBConnection() {}
//
//	public static Connection getConnection() {
//        if (connection == null) {
//            synchronized (DBConnection.class) {
//                if (connection == null) {
//                    String url = "jdbc:mysql://localhost:3305/fwrp";
//                    String username = "root";
//                    String password = "root";
//                    try {
//                        Class.forName("com.mysql.cj.jdbc.Driver");
//                        connection = DriverManager.getConnection(url, username, password);
//                        System.out.println("Post establishing a DB connection - " + connection);
//                    } catch (ClassNotFoundException | SQLException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//        
//        return connection;
//    }
//
//    public static PreparedStatement getPreparedStatement(String sql) {
//        PreparedStatement ps;
//        Connection con = DBConnection.getConnection();
//        try {
//            ps = con.prepareStatement(sql);
//            return ps;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//}



public class DBConnection {

private static Connection connection = null;

// Constructor privado para prevenir instanciación
private DBConnection() {}

private static final String URL = "jdbc:mysql://localhost:3305/fwrp";
private static final String USERNAME = "root";
private static final String PASSWORD = "root";

// Make sure to load the JDBC driver
static {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
        throw new ExceptionInInitializerError(e);
    }
}

public static Connection getConnection() {
    try {
        // Create and return a new connection instance on every call
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    } catch (SQLException e) {
        e.printStackTrace();
        // Consider a better exception handling strategy for your use case
        throw new RuntimeException("Error connecting to the database", e);
    }
}
}