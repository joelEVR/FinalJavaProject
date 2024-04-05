package main.algonquin.cst8288.FinalJavaProject.loginregister;

public class User {

	private int id;
    private String firstname;
    private String username;
    private String email;
    private String password;
    private String userType;

    public User() {
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}