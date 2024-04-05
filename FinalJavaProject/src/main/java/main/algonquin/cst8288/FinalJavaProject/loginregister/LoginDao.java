package main.algonquin.cst8288.FinalJavaProject.loginregister;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

public class LoginDao {

	public String authenticateUser(LoginUser loginUser) {
		String userName = loginUser.getUserName();
		String password = loginUser.getPassword();

		Connection con = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		String userNameDB = "";
		String passwordDB = "";
		String roleDB = "";

		try {
			con = DBConnection.getConnection();
			String query = "SELECT username, password, userType FROM users WHERE username = ?";
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, userName);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				userNameDB = resultSet.getString("username");
				passwordDB = resultSet.getString("password");
				roleDB = resultSet.getString("userType");

				if (userName.equals(userNameDB) && BCrypt.checkpw(password, passwordDB)) {
					return roleDB; // Devuelve el tipo de usuario si la contraseña coincide
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Invalid user credentials";
	}
}