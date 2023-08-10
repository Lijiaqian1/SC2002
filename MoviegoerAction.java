package MOBLIMA;

/**
 * Movie-goer module
 * The interface which movie-goer enter and use the software
 * @author lijiaqian
 *
 */
public class MoviegoerAction extends BaseAction{

	private CineplexLib cineplexLib;
	private MovieLib movieLib;
	private PriceRule priceRule;
	private TicketLib ticketLib;
	
	private String[] mainMenu=new String[] {
			"1: Show cineplex list",
			"2: Show cinema list",		
			"3: Show layout of the cinema",	
			"4: Show movie list",	
			"5: Check seat availability and selection of seat",//Show sessions of the movie
			"6: Book and purchase ticket ",
			"7: View booking history ",		
			"8: Movie review  ",		
			"9: Show top 5 movies",		
			"others: return "};
	/**
	 * Load serialization file
	 */
	public MoviegoerAction() {
		//cineplexLib
		cineplexLib=MyFuns.loadComplexLibFromDataFile();		
		//movieLib
		movieLib=MyFuns.loadMovieLibFromDataFile();		
		//priceRule
		priceRule=MyFuns.loadPriceRuleFromDataFile();
		//ticketLib
		ticketLib=MyFuns.loadTicketLibFromDataFile();
		
	}
	

	private void showMainMenu() {
		MyFuns.printDoubleLine();
		for(String s:mainMenu) {
			System.out.println(s);
		}
		MyFuns.printDoubleLine();
		
	}
	/**
	 * Start the Movie-goer module
	 */
	public void start() {

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
					showCinemaList();
					break;
				case 3://Show layout of the cinema
					showCinemaLayout();
					break;
				case 4://Show movie list
					movieLib.showMoviesBeOn();
					break;
				case 5://Show sessions of the movie
					showSessionList();
					break;
				case 6://Book and purchase ticket
					bookTicket();
					break;
				case 7://View booking history		
					ticketLib.viewhistory();
					break;
				case 8://movie review
					addRatingForAMovie();
					break;
				case 9://Show top 5 movies
					showTopFiveMovie();
					break;
				default:				
					quit=true;
			}
			
			if(quit)
				break;

			//Save Data
			//DataFileUtil.saveToFile(cineplexLib, ".\\cineplexlib.ser");
			DataFileUtil.saveToFile(movieLib, "d:\\MOBLIMA_DATA\\movielib.ser");
			//DataFileUtil.saveToFile(priceRule, ".\\pricerule.ser");
			DataFileUtil.saveToFile(ticketLib, "d:\\MOBLIMA_DATA\\ticketlib.ser");
			MyFuns.showWaitInfo();
		}		
		
	}
	private void addRatingForAMovie() {
		Movie mv=movieLib.selectAMovie(false);
		mv.addRating();
	}
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
	
	private void showCinemaList() {
		//select a cineplex
		Cineplex cx=cineplexLib.selectACineplex();
		if(cx==null)
			return;
		//show all cinema's info in the cineplex
		cx.showCinemaList();

		MyFuns.printSingleLine();
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
	
	private void showSessionList() {
		//select a movie
		System.out.println("----Select a movie");
		movieLib.showMovies();
		Movie mv=movieLib.selectAMovie(true);
		if(mv==null) {
			System.out.println("NA");
			return;
		}
		MyFuns.printStarLine();
		System.out.println("Movie title : "+mv.getMovieTitle());
		MyFuns.printStarLine();
		mv.showSessions();
	}
	private void bookTicket() {
		movieLib.showMoviesBeOn();
		Movie mv=movieLib.selectAMovie(false);
		//mv.showSessions();
		Session ss=mv.selectASession();
		ss.showSession();
		String customername=MyFuns.getInput_str("please input the name",false);
		String seatcode=MyFuns.getInput_str("please input the seatcode you select",false);
		String contactno=MyFuns.getInput_str("please input your mobile number", false);
		String email=MyFuns.getInput_str("please input your email address", false);
		int age=MyFuns.getInput_int("please input your age");
		Ticket ticket=ticketLib.createTicket(customername, contactno,email,age, ss, seatcode);
		if(ticket!=null) {
			ticket.printTicketInfo();
		}
		
			
		
	}

}
