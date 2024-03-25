package main.algonquin.cst8288.FinalJavaProject.service;

import main.algonquin.cst8288.FinalJavaProject.model.Retailer;
import main.algonquin.cst8288.FinalJavaProject.repository.RetailerRepository;

public class RetailerService {
    private final RetailerRepository retailerRepository;

    public RetailerService(RetailerRepository retailerRepository) {
        this.retailerRepository = retailerRepository;
    }

    public void registerRetailer(Retailer retailer) {
        // Business logic for retailer registration
        retailerRepository.save(retailer);
    }

    // Other methods for retailer-related operations
}
