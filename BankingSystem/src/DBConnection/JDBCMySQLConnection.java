package DBConnection;

import java.sql.*;
import com.mysql.jdbc.Statement;
import banking.*;

public class JDBCMySQLConnection {

	
    public static final String URL = "jdbc:mysql://localhost/bankingsystem";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";
    public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver"; 
   
    
    public Connection create_connection() {
        try {
            
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        Connection connection = null;
        
        try {
            
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            //System.out.println("Connected to database");
        } catch (SQLException e) {
            System.out.println("ERROR: Unable to Connect to Database.");
        }
        return connection;
    }  
    
    public float getAccountBalance(int AccountNumber){
    	JDBCMySQLConnection db = new JDBCMySQLConnection();
    	Connection con=db.create_connection();
    	ResultSet rs;
    	float amt = 0;
    	try{
    		Statement statement = (Statement) con.createStatement();
    		
    		 rs= statement.executeQuery("SELECT BalanceAmount FROM account WHERE AccountNumber = "+AccountNumber);
    		while(rs.next()){
    			amt = rs.getFloat("BalanceAmount");
    		}
    		
    	}catch(Exception  e){
    		//e.getMessage();
    		System.out.println("Account Balance transaction failed. Please try again with correct information");
    		
    	}
    
		return amt;}
    
   
    
    public void getAccountHistory(int AccountNumber){
    	JDBCMySQLConnection db = new JDBCMySQLConnection();
    	Connection con=db.create_connection();
    	ResultSet rs;
    	
    	try{
    		Statement statement = (Statement) con.createStatement();
    		
    		 rs= statement.executeQuery("SELECT AccountNumber, BalanceAmount,DateCreated FROM account WHERE AccountNumber = "+AccountNumber);
    		 System.out.println(rs.getInt("AccountNumber"));
    		while(rs.next()){
    			System.out.println(rs.getInt("AccountNumber"));
    			System.out.println(rs.getFloat("BalanceAmount"));
    			Date date = rs.getDate("DateCreated");
    			System.out.println(date);
    			
    		}
    		
    	}catch(Exception  e){
    		
    		System.out.println("Account History Inquiry failed. Please try again with correct information");
    		
    	}
    
		}
    
    public boolean login(String[] user){
    	JDBCMySQLConnection db = new JDBCMySQLConnection();
    	Connection con=db.create_connection();
    	
    	try{
    		
    		System.out.println("Login Details of User:"+ user[0]);
            
    		Statement statement= (Statement) con.createStatement();
    		
    		ResultSet rs= statement.executeQuery("SELECT * FROM user WHERE Username = '"+user[0]+"'");
    		System.out.println(rs.next());
    			
                String username = rs.getString(4);
                String password = rs.getString(5);
                
                if (password.equals(user[1])){
                	System.out.println("Access granted");
                	return true;
                		
                }else{
                	System.out.println("Incorrect Username or password");
                	return false;
                	}
       
    	}catch(Exception  e){
    		System.out.println("Incorrect Username or password.");
    		return false;
    	}
      }
    
    public void addNewUser(User u){
    	JDBCMySQLConnection db = new JDBCMySQLConnection();
    	Connection con=db.create_connection();
	
	try{
		Statement statement = (Statement) con.createStatement();
		
		statement.executeUpdate("INSERT INTO user " + "VALUES  ('"+u.Name+"','"+u.Address+"','"+u.NIC+"','"+u.getUsername()+"','"+u.getPassword()+"')");
		statement.executeUpdate("INSERT INTO account " + "VALUES  ('"+u.AccountNumber+"','"+u.NIC+"',0.0)");
		statement.executeUpdate("INSERT INTO login " + "VALUES  (NULL,'"+u.NIC+"','"+u.getUsername()+"','"+u.getPassword()+"')");
		System.out.println("User is successfully registered");
	}catch(Exception  e){
		//e.getMessage();
		System.out.println("User is successfully registered");
		
	}
}
   
    public void sendMsg(User u){
    	JDBCMySQLConnection db = new JDBCMySQLConnection();
    	Connection con=db.create_connection();
	
	try{
		Statement statement = (Statement) con.createStatement();
		
		statement.executeUpdate("INSERT INTO contactbank " + "VALUES  ('"+u.Name+"','"+u.NIC+"','"+u.AccountNumber+"','"+u.message+"')");
		System.out.println("Your message is successfully recorded");
	}catch(Exception  e){
		
		System.out.println("Server failure. Please try again");
		
	}
}

    public void moneyTransfer(Account a,Account b,float amount) throws InsufficientAccountBalanceException{
    	JDBCMySQLConnection db = new JDBCMySQLConnection();
		Connection con=db.create_connection();
		
		
		try{
    		
			Statement statement1= (Statement) con.createStatement();			
			Statement statement2= (Statement) con.createStatement();
			statement1.executeUpdate("UPDATE `account` SET `BalanceAmount` = `BalanceAmount` - "+amount+" WHERE `AccountNumber` ="+a.accountNumber);
			statement2.executeUpdate("UPDATE `account` SET `BalanceAmount` = `BalanceAmount` + "+amount+" WHERE `AccountNumber` ="+b.accountNumber);
			
    	}catch(Exception  e){
    		System.out.println("Money transfer failed");
    		
    	}
      }

	public void profileinfo(String user[]) {
		JDBCMySQLConnection db = new JDBCMySQLConnection();
    	Connection con=db.create_connection();
    	
    	try{
    		
			System.out.println("Your User Profile :");
            
    		Statement statement= (Statement) con.createStatement();
    		
    		ResultSet rs= statement.executeQuery("SELECT * FROM user WHERE Username = '"+user[0]+"'");
    		while(rs.next()){
    			System.out.println("Name :"+rs.getString("Name"));
    			System.out.println("Name :"+rs.getString("Address"));
    			System.out.println("Name :"+rs.getString("NIC"));
    			System.out.println("Username :"+ user[0]);
    			System.out.println("Password:"+ user[1]);
    			
    		}
    	}catch(Exception  e){
			System.out.println("Incorrect Username or password.");
			
		}
	}
}
 
