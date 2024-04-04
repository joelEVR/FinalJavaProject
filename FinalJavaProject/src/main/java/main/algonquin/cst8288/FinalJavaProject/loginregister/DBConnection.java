package main.algonquin.cst8288.FinalJavaProject.loginregister;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBConnection {

    public static Connection getConnection() {
        Connection con = null;
        // TODO: 23-Jan-20 change the name of the database you want to connect to (needs to have tables USERS,WATCHES).
        //  SQL file will be uploaded within the project under Folder web/SQL_baza
        String url = "jdbc:mysql://localhost:3306/fwrp";
        String username = "username";
        String password = "username";

        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
//                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            con = DriverManager.getConnection(url, username, password);
            System.out.println("Post establishing a DB connection - " + con);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return con;
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