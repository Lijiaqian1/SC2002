package MOBLIMA;

import java.io.File;

/**
 * main program
 * @author Li jiaqian
 *
 */
public class Start {

	public Start() {
		// Check whether the data file exists. If so, load the data
		String fn_cineplexLib=".\\cineplexlib.ser";
		
		File f=new File(fn_cineplexLib);
		if(f.exists()) {
			  //todo:LOAD DATA FROM FILE
		}
	}

	public static void main(String[] args) {
		/**
		 * Main program entry, choose to enter the system as administrator or movie-goer 
		 * 1: administrator;  2: movie-goer;
		 */

		//Initialize the console input object
		MyFuns.initScanner();
		
		boolean quit=false;
		try {
			while(true) {
				MyFuns.printStarLine();
				System.out.println("Welcome to the MOBLIMA system ! \r\n"
						+ "Version: 1.0  \r\n"
						+ "Author:  ");
				MyFuns.printStarLine();
				System.out.print("Please select your identity \n 1: administrator; 2: staff; 3: movie-goer;  others: quit;");
				
				int flag=0;
				flag=MyFuns.getInput_int("");	
				
				switch(flag) {
				case 1://Entering the account management module 
					MyFuns.printStarLine();
					System.out.println("----Account Management Module----");
					MyFuns.printStarLine();
					AccountAction accountAction=new AccountAction();
					accountAction.start();					
					break;
				case 2://Entering the Administrator Module
					MyFuns.printStarLine();
					System.out.println("----Admin Module----");
					MyFuns.printStarLine();
					StaffAction staffAction=new StaffAction();
					staffAction.start();
					break;
				case 3://Entering the Movie-goer Module
					MyFuns.printStarLine();
					System.out.println("----Movie-goer Module----");
					MyFuns.printStarLine();
					MoviegoerAction moviegoerAction=new MoviegoerAction();
					moviegoerAction.start();
					break;
				default:
					quit=true;;
				}
				if (quit)
					break;
			}
		}
		finally {
			
			MyFuns.closeScanner();
		}
		
		System.out.println("Thanks! Bye! ");
		System.out.println("-----MOBLIMA-----");
		

	}

}
