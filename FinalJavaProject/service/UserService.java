package main.algonquin.cst8288.FinalJavaProject.service;

import main.algonquin.cst8288.FinalJavaProject.model.User;
import main.algonquin.cst8288.FinalJavaProject.repository.UserRepository;

public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserService() {
		// TODO Auto-generated constructor stub
	}

	public User findById(Long id) {
        return userRepository.findById(id);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void registerUser(User user) {
        // Additional business logic for user registration
        userRepository.save(user);
    }

    
    public User authenticateUser(String email, String password) {
        // Retrieve user by email from UserRepository
        User user = userRepository.findByEmail(email);

        // Check if user exists and if the provided password matches the stored password hash
        if (user != null && user.getPasswordHash().equals(hashPassword(password))) {
            return user; // Authentication successful
        } else {
            return null; // Authentication failed
        }
    }

    // Utility method to hash the password (You might use a proper hashing algorithm)
    private String hashPassword(String password) {
        // Implement password hashing logic here (e.g., using bcrypt, SHA-256, etc.)
        return password; // For demonstration purposes, returning the plain password as hash
    }
}
