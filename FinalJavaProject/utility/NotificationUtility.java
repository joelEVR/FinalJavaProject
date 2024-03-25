package main.algonquin.cst8288.FinalJavaProject.utility;

import main.algonquin.cst8288.FinalJavaProject.model.User;

public class NotificationUtility {
    
    public void sendEmailNotification(User user, String message) {
        // Code to send email notification to the user
        System.out.println("Sending email notification to " + user.getEmail() + ": " + message);
    }

//    public void sendSmsNotification(User user, String message) {
//        // Code to send SMS notification to the user
//        System.out.println("Sending SMS notification to " + user.getPhoneNumber() + ": " + message);
//    }
}
