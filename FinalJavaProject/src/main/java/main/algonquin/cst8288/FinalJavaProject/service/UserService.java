package main.algonquin.cst8288.FinalJavaProject.service;

import java.util.Optional;

import main.algonquin.cst8288.FinalJavaProject.model.User;
import main.algonquin.cst8288.FinalJavaProject.repository.UserRepository;
import main.algonquin.cst8288.FinalJavaProject.security.HashingUtility;

public class UserService {

    private UserRepository userRepository = new UserRepository();

    public UserService() {
        // Initialize repository or other components if necessary
    }

    public void registerUser(User newUser) {
        // Assuming newUser's password is already hashed
        userRepository.save(newUser);
    }
    
    // Existing registerUser method
    public Optional<User> authenticateUser(String email, String password) {
        UserRepository userRepository = new UserRepository();
        Optional<User> user = userRepository.findByEmail(email);
        
        if (user.isPresent() && HashingUtility.verifyPassword(password, user.get().getPasswordHash())) {
            return user; // Authentication successful
        } else {
            return Optional.empty(); // Authentication failed
        }
    }

	public boolean registerUser(String name, String email, String password, String userType) {
		// TODO Auto-generated method stub
		return false;
	}
}

