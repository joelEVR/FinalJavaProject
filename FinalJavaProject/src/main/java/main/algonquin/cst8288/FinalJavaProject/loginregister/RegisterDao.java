package main.algonquin.cst8288.FinalJavaProject.loginregister;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

public class RegisterDao {

    public String registerUser(User user) {

        //String firstname = user.getName();
        String name = user.getUsername();
        String email = user.getEmail();
        String password = user.getPassword();
        String userType = user.getUserType();
        Boolean notification = user.getNotification();

        Connection con = null;
        PreparedStatement preparedStatement = null;

        try {
            con = DBConnection.getConnection();
            String query = "INSERT INTO users(userID, username, email, password, userType, notification) VALUES (null, ?, ?, ?, ?, ?)";
            preparedStatement = con.prepareStatement(query); //Making use of prepared statements to insert bunch of data
            
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12)); // Encriptar contraseña
            
            //preparedStatement.setString(1, firstname);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, hashedPassword);
            preparedStatement.setString(4, userType);
            preparedStatement.setBoolean(5, notification);

            int i = preparedStatement.executeUpdate();

            if (i != 0)  //Just to ensure data has been inserted into the database
                return "SUCCESS";
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "Oops.. Something went wrong while REGISTER..";
    }
}