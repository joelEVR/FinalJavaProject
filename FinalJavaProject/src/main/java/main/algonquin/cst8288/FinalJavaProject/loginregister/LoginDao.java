package main.algonquin.cst8288.FinalJavaProject.loginregister;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

public class LoginDao {

	public User authenticateUser(LoginUser loginUser) {
		String userName = loginUser.getUserName();
		String password = loginUser.getPassword();

		Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            con = DBConnection.getConnection();
            String query = "SELECT userId, username, password, userType FROM users WHERE username = ?";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, userName);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String userNameDB = resultSet.getString("username");
                String passwordDB = resultSet.getString("password");
                String roleDB = resultSet.getString("userType");
                int userIdDB = resultSet.getInt("userID");

                if (userName.equals(userNameDB) && BCrypt.checkpw(password, passwordDB)) {
                    User user = new User();
                    user.setUserId(userIdDB);
                    user.setUsername(userNameDB);
                    user.setUserType(roleDB);
                    return user; // Devuelve el usuario si la contraseña coincide
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Devuelve null si no se encuentra el usuario o la contraseña no coincide
    }
}
