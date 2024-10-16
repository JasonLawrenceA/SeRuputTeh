package models;

public class User {
	
	private String userid;
	private String username;
	private String password;
	private String address;
	private String phonenumber;
	private String gender;
	
	public User(String userid, String username, String password, String address, String phonenumber, String gender) {
		super();
		this.username = username;
		this.password = password;
		this.address = address;
		this.phonenumber = phonenumber;
		this.gender = gender;
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	

}
