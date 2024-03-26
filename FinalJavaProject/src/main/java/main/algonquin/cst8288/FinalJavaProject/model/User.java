package main.algonquin.cst8288.FinalJavaProject.model;

public class User {
    private Long id;
    private String name;
    private String email;
    private String passwordHash;
    private UserType userType;
    
    
	public User() {	}
	public User(Long id, String name, String email, String passwordHash, UserType userType) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.passwordHash = passwordHash;
		this.userType = userType;
	}
	
	public User(String name2, String email2, String passwordHash2, UserType userType2) {
		this.name = name2;
		this.email = email2;
		this.passwordHash = passwordHash2;
		this.userType = userType2;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPasswordHash() {
		return passwordHash;
	}
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	public UserType getUserType() {
		return userType;
	}
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
    
    

}
