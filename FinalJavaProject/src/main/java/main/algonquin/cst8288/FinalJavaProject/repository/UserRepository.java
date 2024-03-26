package main.algonquin.cst8288.FinalJavaProject.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import main.algonquin.cst8288.FinalJavaProject.model.User;
import main.algonquin.cst8288.FinalJavaProject.model.UserType;
public class UserRepository {
    String url;
	String username;
	String password;
	
    public void save(User user) {

		// Example JDBC code to insert user into database
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (name, email, password, userType) VALUES (?, ?, ?, ?)")) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPasswordHash()); // Consider storing hashed passwords
            preparedStatement.setString(4, user.getUserType().toString());
            
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Add method to find user by email
    // This method returns an optional User object if found!!!!!!
    public Optional<User> findByEmail(String email) {
        User user = null;
        String sql = "SELECT * FROM users WHERE email = ?";
        
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
             
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setPasswordHash(resultSet.getString("password"));
                user.setUserType(UserType.valueOf(resultSet.getString("userType")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return Optional.ofNullable(user);
    }

	public User findById(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}


}
