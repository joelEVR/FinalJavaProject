package main.algonquin.cst8288.FinalJavaProject.loginregister;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

public class LoginDao {

	public User authenticateUser(LoginUser loginUser) {
		String email = loginUser.getEmail();
		String password = loginUser.getPassword();

		Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            con = DBConnection.getConnection();

            String query = "SELECT userId, email, password, userType, notification FROM users WHERE email = ?";

          preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String emailDB = resultSet.getString("email");
                String passwordDB = resultSet.getString("password");
                String roleDB = resultSet.getString("userType");
                int userIdDB = resultSet.getInt("userID");
                Boolean notification = resultSet.getBoolean("notification");

                if (email.equals(emailDB) && BCrypt.checkpw(password, passwordDB)) {
                    User user = new User();
                    user.setUserId(userIdDB);
                    user.setEmail(emailDB);
                    user.setUserType(roleDB);
                    user.setNotification(notification);
                    return user; // Devuelve el usuario si la contraseña coincide
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Devuelve null si no se encuentra el usuario o la contraseña no coincide
    }
}