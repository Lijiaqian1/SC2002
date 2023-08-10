package MOBLIMA;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Admin module
 * The interface which staff enter and use the software
 * @author lijiaqian
 *
 */

//The address of seatlist is  D:\MOBLIMA_DATA\seatlist.txt
public class StaffAction {
	
	CineplexLib cineplexLib;
	MovieLib movieLib;
	PriceRule priceRule;
	TicketLib ticketLib;
	AccountLib accountLib;
	
	Account curAccount;

	private String[] mainMenu=new String[] {
			"1: Show cineplex list",
			"2: New cineplex",
			"3: Show cinema list",
			"4: New cinema",			
			"5: Show layout of the cinema",
			"6: Load layout of the cinema",			
			"7: Show movie list",
			"8: New movie",		
			"9: Update movie's details ",		
			"10: Remove movie ",		
			"11: Show sessions of the movie ",		
			"12: New session of the movie ",			
			"13: Remove session of the movie ",			
			"14: Holiday setting ",			
			"15: Show top 5 movies",	
			"16: Change password ",
			"others: return "
			};
	
	/**
	 * Load serialization file
	 */
	public StaffAction() {
		//cineplexLib
		cineplexLib=MyFuns.loadComplexLibFromDataFile();		
		//movieLib
		movieLib=MyFuns.loadMovieLibFromDataFile();		
		//priceRule
		priceRule=MyFuns.loadPriceRuleFromDataFile();
		//ticketLib
		ticketLib=MyFuns.loadTicketLibFromDataFile();
		//accountLib
		accountLib=MyFuns.loadAccountLibFromDataFile();	
		
	}

	/**
	 * Check the administrator password and exit if you enter incorrect passwords for three times
	 * @return if login success ,return true
	 */
	private boolean login() {
		
		if (accountLib.getAccountsCount()==0) {
			System.out.println("The account list is empty. Please log in as the administrator and add the staff account ");
			return false;
		}
		Account account=null;
		while(account==null) {
			account=accountLib.selectAnAccount();
		}
		
		System.out.printf("Please enter the password\r\n");

		int errNum=0;
		while(errNum<3) {
			String pwd=MyFuns.getInput_str("password:",false);
			if (pwd.equals(account.getPwd())) {
				//Record the current account
				curAccount=account;
				return true;
			}
			else{
				errNum++;
				if(errNum<3)
					System.out.printf("You've entered the wrong password %d times,Please re-enter the password\r\n",errNum);
				else {
					System.out.printf("You've entered the wrong password %d times,can not continue\r\n",errNum);
				}					
			}
		}
		
		return false;
	}

	
	private void showMainMenu() {
		MyFuns.printDoubleLine();
		for(String s:mainMenu) {
			System.out.println(s);
		}
		MyFuns.printDoubleLine();
		
	}
	/**
	 * Start the Admin module
	 */
	public void start() {
		if (!login())
			return;
		while(true) {
			//show main menu 
			showMainMenu();
			int flag;
			boolean quit=false;
			flag=MyFuns.getInput_int("Please enter the menu number to continue:");
			//Displays the selected menu-items
			if(flag>=1 && flag<=mainMenu.length-1) {
				System.out.println("");
				MyFuns.printStarLine();
				System.out.println("\t"+mainMenu[flag-1]);
				MyFuns.printStarLine();
			}
			System.out.println("");
			
			switch(flag) {
				case 1://Show cineplex list
					cineplexLib.showCineplexes();
					break;
				case 2://New cineplex
					cineplexLib.newCineplex();
					break;
				case 3://show cinema list;
					showCinemaList();
					break;
				case 4://New Cinema
					createACinema();
					break;
				case 5://Show layout
					showCinemaLayout();
					break;
				case 6://Load layout
					loadCinemaLayout();
					break;
				case 7://Show movie list;
					movieLib.showMovies();
					break;
				case 8://New movie
					movieLib.newMovie();
					break;
				case 9://Update movie's details
					movieLib.updateMovieInfo();
					break;
				case 10://Remove movie
					movieLib.removeMovie();
					break;
				case 11://Show sessions of the movie
					showSessionList();
					break;
				case 12://New session of the movie
					newSession();
					break;
				case 13://Remove session of the movie
					removeASession();
					break;
				case 14://Holiday setting 
					holidaySetting();
					break;
				case 15://Show top 5
					showTopFiveMovie();
					break;
				case 16://change password
					curAccount.changeAccountPwd();
					break;
				default:				
					quit=true;
			}
			
			if(quit)
				break;
			//Save Data
			DataFileUtil.saveToFile(cineplexLib, "d:\\MOBLIMA_DATA\\cineplexlib.ser");
			DataFileUtil.saveToFile(movieLib, "d:\\MOBLIMA_DATA\\movielib.ser");
			DataFileUtil.saveToFile(priceRule, "d:\\MOBLIMA_DATA\\pricerule.ser");
			DataFileUtil.saveToFile(ticketLib, "d:\\MOBLIMA_DATA\\ticketlib.ser");
			DataFileUtil.saveToFile(accountLib, "d:\\MOBLIMA_DATA\\accountLib.ser");
			
			MyFuns.showWaitInfo();
		}
				
	}
	
	private void showCinemaList() {
		//select a cineplex
		Cineplex cx=cineplexLib.selectACineplex();
		if(cx==null)
			return;
		//show all cinema's info in the cineplex
		cx.showCinemaList();

		MyFuns.printSingleLine();
	}
	

	private void createACinema() {
		//select a cineplex
		Cineplex cx=cineplexLib.selectACineplex();
		if(cx==null)
			return;
		//show all cinema's info in the cineplex
		Cinema cn=cx.newCinema();
		
		if (cn!=null)
			System.out.println("Create new cinema success !");

		MyFuns.printSingleLine();
	}
	/**
	 * User first select a movie, then choose the cineplex and cinema.  
	 * User can continuously input the time of the session 
	 */
	private void newSession() {
		//select a movie
		System.out.println("----Select a movie:");
		movieLib.showMovies();
		Movie mv=movieLib.selectAMovie(false);
		if(mv==null)
			return;
		
		//select a cineplex
		System.out.println("----Select a cineplex:");
		Cineplex cx=cineplexLib.selectACineplex();
		if(cx==null)
			return;
		//show all cinema's info in the cineplex
		cx.showCinemaList();
		System.out.println("----Select a cinema:");
		Cinema cn=cx.selectACinema();
		if(cn==null)
			return;
		
		Calendar showtime;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		while(true) {
			System.out.println("Please enter the movie showtime  or press Enter to exit:");
			String timeStr=MyFuns.getInput_str("showtime format: 'yyyy-MM-dd HH:mm' : ", true);
			
			if (timeStr.equals(""))
				break;
			
			timeStr=timeStr+":00";			
			try {
				showtime=Calendar.getInstance();
				showtime.setTime(sdf.parse(timeStr));
				mv.newSession(cn, showtime);
				System.out.println("create session success.");
	
			} catch (ParseException e) {
				System.out.println("show time invalid.");
			}
		}
	}
	
	private void showSessionList() {
		//select a movie
		System.out.println("----Select a movie");
		movieLib.showMovies();
		Movie mv=movieLib.selectAMovie(true);
		
		if(mv==null) {
			System.out.println("NA");
			return;
		}
		System.out.println("Movie name : "+mv.getMovieTitle());
		mv.showSessions();
	}
	
	private void removeASession() {
		//select a movie
		System.out.println("----Select a movie");
		movieLib.showMovies();
		Movie mv=movieLib.selectAMovie(true);
		if(mv==null) {
			System.out.println("There are no valid movie");
			return;
		}
	
		Session sn=mv.selectASession();
		if(sn==null) {
			System.out.println("There are no valid session");
			return;
		}
		if(MyFuns.confirmDlg("Remove session : "+sn.getsessionID()+" of movie : "+mv.getMovieTitle()+"?(Y or N)")) {
			mv.removeSession(sn);
		}
		
	}
	
	private void showCinemaLayout() {
		//select a cineplex
		System.out.println("----Select a cineplex:");
		Cineplex cx=cineplexLib.selectACineplex();
		if(cx==null)
			return;
		//show all cinema's info in the cineplex
		cx.showCinemaList();
		System.out.println("----Select a cinema:");
		Cinema cn=cx.selectACinema();
		if(cn==null){
			System.out.println("NA");
			return;
		}
		cn.printSeatLayout();		
	}

	private void loadCinemaLayout() {
		//select a cineplex
		System.out.println("----Select a cineplex:");
		Cineplex cx=cineplexLib.selectACineplex();
		if(cx==null)
			return;
		//show all cinema's info in the cineplex
		cx.showCinemaList();
		System.out.println("----Select a cinema:");
		Cinema cn=cx.selectACinema();
		if(cn==null) {
			System.out.println("cinema invalid");
			return;
		}
		
		cn.loadSeatsFromFile();
		
	}
	/**
	 * Staff can set holidays
	 */
	private void holidaySetting() {
		while(true) {
			priceRule.showPHs();
			MyFuns.printSingleLine();
			int flag=MyFuns.getInput_int("Select operation : 1: remove a holiday 2: add a holiday 3: quit ");
			switch(flag) {
			case 1:
				priceRule.removePHs();
				break;
			case 2:
				priceRule.InputPHs();
				break;
			case 3:
				return;
			default:
				continue;
			}
		}
	}
	/**
	 * Operators can select sales or rating as standard to sort the movies and get top 5 movies
	 */
	private void showTopFiveMovie() {
		int flag=MyFuns.getInput_int("Please input the sort mode: 1:by ticket sales; 2:by overall rating ");
		if(flag!=1 && flag!=2) {
			System.out.println("input invalid");
			return;
		}
		else if (flag==1) {
			movieLib.showTopFiveMovieBySales();
		}
		else if (flag==2) {
			movieLib.showTopFiveMovieByRating();
		}
	}
}
