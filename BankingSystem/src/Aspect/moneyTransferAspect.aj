package Aspect;

import banking.Account;

public aspect moneyTransferAspect {

	pointcut monitor(Account account,float amount):call (void Account.credit(float)) && target(account)&& args(amount);
	
	 before(Account account,float amount) : monitor(account,amount){
		 
		 if(account.getAccountBalance()<amount){
		  System.out.println("Transaction cannot be completed. Your account balance is :" + account.getAccountBalance());
		 }
		 else{
		 System.out.println("Transaction is successful");
		 }
	  }
}
