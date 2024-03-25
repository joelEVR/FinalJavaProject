package main.algonquin.cst8288.FinalJavaProject.repository;

import java.util.List;

import main.algonquin.cst8288.FinalJavaProject.model.FoodItem;

public interface FoodItemRepository {
    FoodItem findById(Long id);
    List<FoodItem> findByRetailerId(Long retailerId);
    List<FoodItem> findSurplusItems();
    
    List<FoodItem> findAll();
    
    void save(FoodItem item);
    void update(FoodItem item);
    void delete(Long id);
    // Other methods as needed for data access operations
}
