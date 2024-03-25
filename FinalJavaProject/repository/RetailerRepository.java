package main.algonquin.cst8288.FinalJavaProject.repository;

import main.algonquin.cst8288.FinalJavaProject.model.Retailer;

public interface RetailerRepository {
    Retailer findByEmail(String email);
    void save(Retailer retailer);
    // Other methods as needed
}
