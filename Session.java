package MOBLIMA;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

/**
 * The session information for the movie, which is contained in the Movie class
 * @author lijiaqian
 *
 */
public class Session implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5537328181272787879L;
	private Movie parentMovie;
	private Cinema parentCinema;

	private int SessionID;

	private Calendar showtime;
	private Seat[][] seats;

	/**
	 * 
	 * @param movie
	 * @param cinema
	 * @param showtime
	 * @param SessionID
	 */
	public Session(Movie movie,Cinema cinema,Calendar showtime,int SessionID) {
		this.parentMovie=movie;
		this.parentCinema=cinema;
		this.showtime=showtime;		
		this.SessionID=SessionID;
				
		this.seats=new Seat[0][0];
		
		//copy seats from the cinema
		Seat[][] cinemaSeats=cinema.getSeatarr();
		this.seats=Arrays.copyOf(cinemaSeats,cinemaSeats.length);
		//Copy every row, otherwise it's a reference
		for(int i=1;i<=cinemaSeats.length;i++) {
			this.seats[i-1]=Arrays.copyOf(cinemaSeats[i-1], cinemaSeats[i-1].length);
		}

	}
	public void setParentMovie(Movie value) {
		this.parentMovie=value;
	}
	public void setParentCinema(Cinema value) {
		this.parentCinema=value;
	}
	public Movie getParentMovie() {
		return this.parentMovie;
	}
	public Cinema getParentCinema() {
		return this.parentCinema;
	}
	
	public String getDate() {
		return String.valueOf(showtime.get(Calendar.MONTH)+1)+"-"+  //Jan is 0
				String.valueOf(showtime.get(Calendar.DAY_OF_MONTH));
	}
	public MyEnum.Week getWeek(){		
		return MyEnum.intToWeek(showtime.get(Calendar.DAY_OF_WEEK));//sunday is zero
	}
	public int getsessionID() {
		return this.SessionID;
	}
	/**
	 * To mark a seat as selected when a seat is booked
	 */
	public void bookSelectedSeatOfSession(String SeatCode,String tid) {
		for(int i=0;i<seats.length;i++) {
			for(int j=0;j<seats[i].length;j++) {
				if(seats[i][j].getseatCode().equals(SeatCode)) {
					seats[i][j].bookSeat(tid);
				}
			}
		}
	}
	public void showSession() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		System.out.println("SessionID : "+SessionID+" : showtime : "+sdf.format(showtime.getTime())+
						" , cineplex : "+parentCinema.getParentCineplex().getCineplexName()+" , cinema : "+parentCinema.getCinemaCode());
		MyFuns.printSingleLine();
		System.out.println("\t\t<====== Screen =====>");
		printSeatLayout();
		System.out.println("\nnote:  [*] : Sold ; [o] : Available ");
		//System.out.println("cineplex code is "+parentCinema.getParentCineplex().getCineplexCode());
		//System.out.println("cinema is "+parentCinema.getCinemaCode());
	}	
	public void printSeatLayout() {
		for(int i=0;i<seats.length;i++) {
			for(int j=0;j<seats[i].length;j++) {
				if(seats[i][j].getisSeat()=='F') {
					System.out.printf("x\t");
				}else if(seats[i][j].getisSeat()=='T') {
					System.out.printf("Stair\t");
				}else if(seats[i][j].getisSeat()=='A') {
					System.out.printf("Aisle\t");
				}else if(seats[i][j].getisSeat()=='S') {
					System.out.printf(seats[i][j].getseatCode());
					if(seats[i][j].getisSelected()==true) {
						System.out.printf("[*]\t");//* means selected
					}else{
						System.out.printf("[o]\t");//o means unselected
					}					
				}
				
			}
			System.out.printf("\n");
		}
	}
	/**
	 * Count the number of tickets sold for the session
	 */
	public int getSeatNumSelected() {
		int n=0;
		for(Seat[] sts : seats) {
			for (Seat st : sts) {
				if(st.getisSelected())
					n++;
			}
			
		}
		return n;
	}
	
}
