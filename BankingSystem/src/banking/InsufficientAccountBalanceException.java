package banking;

	public class InsufficientAccountBalanceException extends Exception {
		public InsufficientAccountBalanceException(String message) {
			//super(message);
			System.out.println("Your Account Balance is not sufficent to withdraw");
		}
	}

