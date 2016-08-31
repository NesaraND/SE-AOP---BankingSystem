package Aspect;

import banking.User;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public aspect systemloggerAspect {
	

		pointcut Logging(User user) : call(User User.login(String[])) && target(user);
		  after(User user) : Logging(user){
			
			WriteFile("Login - Username:- "+user.getUsername()+" Password:- "+user.getPassword()+" Success");
		  }


		  public void WriteFile(String Log){
			  try {
				    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("logfile.txt", true)));
				    out.println(Log);
				    out.close();
				} catch (IOException e) {
				    System.out.println("Incorrect Username or password");
				}
		  }
}







