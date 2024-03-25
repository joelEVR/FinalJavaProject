package main.algonquin.cst8288.FinalJavaProject.repository;

import main.algonquin.cst8288.FinalJavaProject.model.CharitableOrg;

public interface CharitableOrgRepository {
    CharitableOrg findByEmail(String email);
    void save(CharitableOrg charitableOrg);
    // Other methods as needed
}
