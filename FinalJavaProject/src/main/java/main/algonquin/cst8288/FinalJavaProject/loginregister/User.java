package main.algonquin.cst8288.FinalJavaProject.loginregister;

public class User {

  	private int userId;
    private String name;
    private String email;
    private String password;
    private String userType;
    private String location;
  	boolean notification;

    public User() {
    }
  
   public int getUsername() {
	    	return userName;
	  }

	  public void setUserName(int userName) {
	    	this.userName = userName;
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
  
    public String getLocation() {
	  	  return location;
	  }

	  public void setLocation(String location) {
	  	  this.location = location;
	  }
  
   public boolean isNotification() {
	    	return notification;
   }

	  public void setNotification(boolean notification) {
	    	this.notification = notification;
	  }

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}