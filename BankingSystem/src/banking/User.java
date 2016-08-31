package banking;

import java.sql.SQLException;

import DBConnection.JDBCMySQLConnection;

public class User {


	public String Name, Address, NIC;
	String username;
	String password;
	public boolean isloggedIn;
	public int AccountNumber;
	public String message;
	
	public User(String username,String password){
		this.username=username;
		this.password=password;
		
	}
	public User(String password){
		
		this.password=password;
	}
	
	public User(){}

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
	
	void isloggedIn(boolean loginStatus){
		this.isloggedIn = loginStatus;
		System.out.println("Logged in"+this.isloggedIn);
		
	}
	
	public User login(String[] user){
		
		JDBCMySQLConnection db = new JDBCMySQLConnection();
		isloggedIn= db.login(user);		
		
		//System.out.println(isloggedIn);
		this.isloggedIn=isloggedIn;
		
		return this;
		
	}
	
	public void registration(User u){
		System.out.println("User registration");
		JDBCMySQLConnection db = new JDBCMySQLConnection();
		db.addNewUser(u);
		
	}

	public void contactBank(User u){
		
		JDBCMySQLConnection db = new JDBCMySQLConnection();
		db.sendMsg(u);
		
	}
	
	public boolean getIsloggedin(User u) {
		
		return this.isloggedIn;
	}
	
	public void profileMngt(String[] user){
		JDBCMySQLConnection db = new JDBCMySQLConnection();
		db.profileinfo(user);
	}

	
}