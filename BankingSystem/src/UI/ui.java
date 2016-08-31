package UI;

import java.util.Scanner;
import banking.Account;
import banking.InsufficientAccountBalanceException;
import banking.User;

public class ui {
	
	public void MainUI(){
		Scanner s = new Scanner(System.in);
		
		this.firstOptions(s);
   
	}
	
	public void firstOptions(Scanner s){
		System.out.println("Ayubowan! Welcome to Lanka Bank (PVT) Ltd.");
		System.out.println("Please select an option");
		System.out.println("1. User Login ");
		System.out.println("2. User Regitration ");
		System.out.println("3. Exit ");
		int option = s.nextInt();
		
		switch(option){
		
		case 1: this.loginUI();
		break;
		case 2: this.registrationUI();
		break;
		case 3: s.close(); 
		
		}
		
	}
	
	public void subOptions(User u, Scanner s){
		System.out.println("3. Money Transfer to other accounts ");
		System.out.println("4. Account Inquiries  ");
		System.out.println("5. Account History Inquiries");
		System.out.println("6. Personal Profile management ");
		System.out.println("7. Contact bank ");
		System.out.println("8. Exit ");
		
		int option = s.nextInt();
		
		switch(option){
		case 3: this.moneyTransferUI();break;
		case 4:	this.accountInquiryUI();break;
		case 5:	this.accountHistoryInquiryUI();break;
		case 6:	this.profileMngtUI();break;
		case 7:	this.contactBankUI();break;
		case 8:	s.close(); 
		
		}
		
	}

	
	public User loginUI(){
		Scanner s = new Scanner(System.in);
		
		String[] user= new String[2]; 
		
		System.out.println("Username :");
		user[0]= s.next();
		
		System.out.println("Password :");
		user[1] = s.next();
		
		User newUser = new User(user[0],user[1]);
		newUser.login(user);
		
		if(newUser.getIsloggedin(newUser)){
			this.subOptions(newUser,s);
		
		}
		
		return newUser;
		
	}
	
	public void registrationUI(){
		Scanner s = new Scanner(System.in);
		User u = new User();
		System.out.println("Enter Name: ");
		u.Name = s.next();
		System.out.println("Enter Address: ");
		u.Address = s.next();
		System.out.println("Enter your NIC: ");
		u.NIC = s.next();
		System.out.println("Enter your Account Number: ");
		u.AccountNumber = s.nextInt();
		System.out.println("Username: ");
		u.setUsername(s.next()) ;
		System.out.println("Password: ");
		u.setPassword(s.next());
		u.registration(u);
		this.subOptions(u,s);
		if(u.getIsloggedin(u)){
			this.subOptions(u,s);
		
		}

	}
	
	public void accountInquiryUI(){

		Scanner s = new Scanner(System.in);
		System.out.println("Enter Account Number : ");
		int input = s.nextInt();

		Account account1=new Account(input);
		System.out.println("Your current Account balance is : Rs. "+ account1.getAccountBalance());
		
		User u = new User();
		System.out.println("Please select an option below to proceed further. Else select Exit");
		this.subOptions(u,s);
	}
	
	public void accountHistoryInquiryUI(){
		Scanner s = new Scanner(System.in);
		System.out.println("Enter Account Number : ");
		int input = s.nextInt();
		
		Account account1=new Account(input);
		
		System.out.println("Account Number :"+account1.getAccountNumber());
		System.out.println("Account Balance :"+account1.getAccountBalance());
		//Account.getAccountHistory(input);
		//System.out.println("Account Created Date :"+account1.getDateCreated());
		
		User u = new User();
		System.out.println("Please select an option below to proceed further. Else select Exit");
		this.subOptions(u,s);
	}
	
	public void moneyTransferUI(){
		
		Scanner s = new Scanner(System.in);
		
		System.out.println("Enter Account Number to credit: ");
		int input1 = s.nextInt();
		System.out.println("Enter Account Number to debit: ");
		int input2 = s.nextInt();
		System.out.println("Enter amount: ");
		float input3 = s.nextFloat();
			
			
		Account account1=new Account(input1);
		Account account2=new Account(input2);
		try {
			Account.MoneyTransfer(account1,account2,input3);
		} catch (InsufficientAccountBalanceException e) {
				
			e.printStackTrace();
		}
		User u = new User();
		System.out.println("Please select an option below to proceed further. Else select Exit");
		this.subOptions(u,s); 
		
	}
	
	public User profileMngtUI() {
		
		Scanner s = new Scanner(System.in);
		String[] user= new String[2]; 
		
		System.out.println("Username :");
		user[0]= s.next();
		
		System.out.println("Password :");
		user[1] = s.next();
		
		User newUser1 = new User(user[0],user[1]);
		newUser1.profileMngt(user);
		
		System.out.println("Please select an option below to proceed further. Else select Exit");
		this.subOptions(newUser1,s);
		
		return newUser1;
		
	}
	
	public void contactBankUI(){
		Scanner s = new Scanner(System.in);
		User u = new User();
		System.out.println("Enter Name: ");
		u.Name = s.next();
		System.out.println("Enter your NIC: ");
		u.NIC = s.next();
		System.out.println("Enter your Account Number: ");
		u.AccountNumber = s.nextInt();
		System.out.println("Enter Message: ");
		u.message = s.next();
	
		u.contactBank(u);
		
		System.out.println("Please select an option below to proceed further. Else select Exit");
		this.subOptions(u,s);
		

	}
	public static void main(String a[]){
		
		ui Newui = new ui();
		Newui.MainUI();
		System.out.println("end");
		System.out.println("Thank you! Have a nice day");
		
		
	}
}
