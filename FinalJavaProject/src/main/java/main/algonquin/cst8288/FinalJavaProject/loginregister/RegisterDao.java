package main.algonquin.cst8288.FinalJavaProject.loginregister;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterDao {

    public String registerUser(User user) {

        String firstname = user.getFirstname();
        String username = user.getUsername();
        String email = user.getEmail();
        String password = user.getPassword();
        String userType = user.getUserType();

        Connection con = null;
        PreparedStatement preparedStatement = null;

        try {
            con = DBConnection.getConnection();
            String query = "INSERT INTO users(id, firstname, username, email, password, userType) VALUES (null, ?, ?, ?, ?, ?)";
            preparedStatement = con.prepareStatement(query); //Making use of prepared statements to insert bunch of data
            preparedStatement.setString(1, firstname);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, password);
            preparedStatement.setString(5, userType);

            int i = preparedStatement.executeUpdate();

            if (i != 0)  //Just to ensure data has been inserted into the database
                return "SUCCESS";
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "Oops.. Something went wrong while REGISTER..";
    }
}