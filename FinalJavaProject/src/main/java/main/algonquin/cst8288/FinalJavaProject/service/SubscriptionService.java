package main.algonquin.cst8288.FinalJavaProject.service;

import java.util.List;

import main.algonquin.cst8288.FinalJavaProject.model.Subscription;
import main.algonquin.cst8288.FinalJavaProject.repository.SubscriptionRepository;

public class SubscriptionService {
    private SubscriptionRepository subscriptionRepository;

    public SubscriptionService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    public void addSubscription(Subscription subscription) {
        subscriptionRepository.save(subscription);
    }

    public List<Subscription> getEligibleSubscriptionsForAlert(String location, String foodPreferences) {
        return subscriptionRepository.findByLocationAndFoodPreferences(location, foodPreferences);
    }

	public List<Subscription> getAllSubscriptions() {
		// TODO Auto-generated method stub
		return null;
	}
}

