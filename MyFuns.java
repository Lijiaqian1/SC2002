package MOBLIMA;

import java.io.File;
import java.util.Scanner;

/**
 * 
 * @author lijiaqian
 * Contains some common functions
 */
public class MyFuns {
	
	private static Scanner mysc;
	
	public static void initScanner() {
		mysc=new Scanner(System.in);
	}
	
	public static void closeScanner() {
		mysc.close();
	}
	
	/**
	 * load cineplexLib from datafile
	 * Check whether the data file exists. If so, load the data
	 */
	public static CineplexLib loadComplexLibFromDataFile() {
		CineplexLib cineplexLib;
		
		File f;
		String fn;
		//cineplexLib
		
		fn="d:\\MOBLIMA_DATA\\cineplexlib.ser";		
		f=new File(fn);
		if(f.exists()) {
			try {
				cineplexLib=(CineplexLib)(DataFileUtil.readFromFile(fn));
			}
			catch (Exception e){
				cineplexLib=new CineplexLib();
			}
		}
		else
			cineplexLib=new CineplexLib();
		return cineplexLib;
	}
		

	/**
	 * load movieLib from datafile
	 * Check whether the data file exists. If so, load the data
	 */
	public static MovieLib loadMovieLibFromDataFile() {
		MovieLib movieLib;
		
		File f;
		String fn;

		fn="d:\\MOBLIMA_DATA\\movieLib.ser";		
		f=new File(fn);
		if(f.exists()) {
			try {
				movieLib=(MovieLib)(DataFileUtil.readFromFile(fn));
			}
			catch (Exception e){
				movieLib=new MovieLib();
			}
		}
		else
			movieLib=new MovieLib();
		return movieLib;
	}

	/**
	 * load priceRule from datafile
	 * Check whether the data file exists. If so, load the data
	 */
	public static PriceRule loadPriceRuleFromDataFile() {
		PriceRule priceRule;
		
		File f;
		String fn;

		fn="d:\\MOBLIMA_DATA\\pricerule.ser";		
		f=new File(fn);
		if(f.exists()) {
			try {
				priceRule=(PriceRule)(DataFileUtil.readFromFile(fn));
			}
			catch (Exception e){
				priceRule=new PriceRule();
			}
		}
		else
			priceRule=new PriceRule();
		return priceRule;
	}
	

	/**
	 * load ticketLib from datafile
	 * Check whether the data file exists. If so, load the data
	 */
	public static TicketLib loadTicketLibFromDataFile() {
		TicketLib ticketLib;
		
		File f;
		String fn;
		fn="d:\\MOBLIMA_DATA\\ticketlib.ser";		
		f=new File(fn);
		if(f.exists()) {
			try {
				ticketLib=(TicketLib)(DataFileUtil.readFromFile(fn));
			}
			catch (Exception e){
				ticketLib=new TicketLib();
			}
		}
		else
			ticketLib=new TicketLib();
		return ticketLib;
	}

	 /**
	  * load accountLib from datafile
	  * Check whether the data file exists. If so, load the data
	  */
	 public static AccountLib loadAccountLibFromDataFile() {
	  AccountLib accountLib;
	  
	  File f;
	  String fn;
	  //cineplexLib
	  
	  fn="d:\\MOBLIMA_DATA\\accountLib.ser";  
	  f=new File(fn);
	  if(f.exists()) {
	   try {
	    accountLib=(AccountLib)(DataFileUtil.readFromFile(fn));
	   }
	   catch (Exception e){
	    accountLib=new AccountLib();
	   }
	  }
	  else
	   accountLib=new AccountLib();
	  return accountLib;
	 }
	/**
	 * A common method to enter integers
	 * @param hint :Prompt message when a user enters a integer
	 * @return
	 */
	public static int getInput_int(String hint) {
		
		String s="";
		while(s.equals("")) {
			s=getInput_str(hint,false);
			try {
				return Integer.parseInt(s);
			}
			catch(Exception e){
				s="";
			}
		}
		return -999;
		

	}
	/**
	 * A common method to enter a string
	 * @param hint :Prompt message when a user enters a string
	 * @param allownull :Indicates whether the user can enter an empty string
	 * @return
	 */	
	public static String getInput_str(String hint,boolean allownull) {
		String s;
		while(true) {
			if(hint!="")
				System.out.print(hint);
			s=mysc.nextLine().trim();
			if(s.equals("")&&(!allownull))
				continue;
			else
				break;			
		}
		return s;
	}
	/**
	 * Check whether the user confirms the request
	 * @param hint
	 * @return boolean: if first char is 'Y'or'y', return true
	 */
	public static boolean confirmDlg(String hint) {
		MyFuns.printSingleLine();
		String s=getInput_str(hint,false);
		s=s.toUpperCase();
		MyFuns.printSingleLine();
		
		return (s.charAt(0)=='Y')?true:false;
		
	}
	/**
	 * Displays a pause message waiting for the user to press Enter
	 */
	public static void showWaitInfo() {
		System.out.println("\nPress any key to continue...");
		mysc.nextLine();
	}
	/**
	 * Displays a double horizontal line
	 */
	public static void printDoubleLine() {
		System.out.println("==================================================");
	}
	/**
	 * Displays a single horizontal line
	 */
	public static void printSingleLine() {
		System.out.println("--------------------------------------------------");
	}
	/**
	 * Displays a star horizontal line
	 */
	public static void printStarLine() {
		System.out.println("**************************************************");
	}
}
