package main.algonquin.cst8288.FinalJavaProject.repository;

import main.algonquin.cst8288.FinalJavaProject.model.User;

public interface UserRepository {
    User findById(Long id);
    User findByEmail(String email);
    void save(User user);
    void update(User user);
    void delete(Long id);

}
