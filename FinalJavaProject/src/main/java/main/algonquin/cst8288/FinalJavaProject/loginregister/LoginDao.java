package main.algonquin.cst8288.FinalJavaProject.loginregister;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginDao {

    public String authenticateUser(LoginUser loginUser) {
        String userName = loginUser.getUserName();
        String password = loginUser.getPassword();

        Connection con = null;
        Statement statement = null;
        ResultSet resultSet = null;

        String userNameDB = "";
        String passwordDB = "";
        String roleDB = "";

        try {
            con = DBConnection.getConnection();
            statement = con.createStatement();
            resultSet = statement.executeQuery("SELECT username, password, userType FROM users");

            while (resultSet.next()) {
                userNameDB = resultSet.getString("username");
                passwordDB = resultSet.getString("password");
                roleDB = resultSet.getString("userType");

                if (userName.equals(userNameDB) && password.equals(passwordDB) && "RETAILER".equals(roleDB))
                    return "RETAILER";
                else if (userName.equals(userNameDB) && password.equals(passwordDB) && "CONSUMER".equals(roleDB))
                    return "CONSUMER";
                else if (userName.equals(userNameDB) && password.equals(passwordDB) && "CHARITY".equals(roleDB))
                    return "CHARITY";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Invalid user credentials";
    }
}