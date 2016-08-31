package banking;



import java.util.Date;

import DBConnection.JDBCMySQLConnection;

public class Account {
	
	public int accountNumber;
	public float accountBalance;
	public float amount;
	public Date dateCreated;
	
	
	
	public Account(int accNumber){
		accountNumber = accNumber;
		JDBCMySQLConnection db = new JDBCMySQLConnection();
		accountBalance=db.getAccountBalance(accountNumber);
		Date dateCreated = new Date();
		
	}
	
	public static void getAccountHistory(int accountNumber){
		JDBCMySQLConnection db = new JDBCMySQLConnection();
		db.getAccountHistory(accountNumber);
	}

	public void debit(float amount){
		accountBalance+= amount;
	}

	public void credit(float amount)throws InsufficientAccountBalanceException{
		if (accountBalance < amount){
			throw new InsufficientAccountBalanceException("Account Balance is not sufficient to withdraw");
		}else {
			accountBalance-= amount;
		}
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public float getAccountBalance() {
		return accountBalance;
	}
	
	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public void setAccountBalance(float accountBalance) {
		this.accountBalance = accountBalance;
	}
	
	public static void MoneyTransfer(Account a,Account b,float amount) throws InsufficientAccountBalanceException{
		
		 a.credit(amount);
		 b.debit(amount);
		 JDBCMySQLConnection db = new JDBCMySQLConnection();
		db.moneyTransfer(a, b, amount);
	 }
	
}
