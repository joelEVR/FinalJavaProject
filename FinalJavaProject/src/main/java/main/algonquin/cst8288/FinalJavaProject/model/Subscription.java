package main.algonquin.cst8288.FinalJavaProject.model;

public class Subscription {
    private Long id;
    private Long userId;
    private String location;
    private String communicationMethod; // "email" or "sms"
    private String foodPreferences; // Could be a serialized list or specific criteria

    // Constructors, getters, and setters
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCommunicationMethod() {
		return communicationMethod;
	}
	public void setCommunicationMethod(String communicationMethod) {
		this.communicationMethod = communicationMethod;
	}
	public String getFoodPreferences() {
		return foodPreferences;
	}
	public void setFoodPreferences(String foodPreferences) {
		this.foodPreferences = foodPreferences;
	}

    
    
}
