package main.algonquin.cst8288.FinalJavaProject.loginregister;

public class User {

	private int userID;
    private String username;
    private String email;
    private String password;
    private String userType;
    private String location;
    private boolean notification;

    public User() {
    }


    public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public int getUserID() {
		return userID;
	}


	public void setUserID(int userID) {
		this.userID = userID;
	}


	public boolean getNotification() {
		return notification;
	}


	public void setNotification(boolean notification) {
		this.notification = notification;
	}


	public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

}