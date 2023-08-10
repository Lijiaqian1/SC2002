package MOBLIMA;

import java.io.Serializable;

/**
 * 
 * Enumeration types used in the project, and some related functions
 * 
 * The static methods are used to display a list of each enumeration, 
 * including the ordinal number (starting at 0) and name of each item, 
 * as well as a method to convert an integer to an enumeration type 
 * (to convert an integer entered by the user to its corresponding enumeration value) 
 * 
 * @author lijiaqian
 *
 */
public class MyEnum{
	/**
	 * Enumeration of movie types
	 * 
	 * @author lijiaqian
	 *
	 */
	public enum MovieType implements Serializable {
		MT_3D,
		MT_Blockbuster,
		MT_Simple
	}
	/**
	 * Enumeration of movie ratings
	 * 
	 * @author sunmingzhong
	 *
	 */
	public enum MovieRating implements Serializable{
		RA_G,
		RA_PG,
		RA_PG_13,
		RA_R,
		RA_NC_17
	}
	/**
	 * Enumeration of movie status
	 * @author lijiaqian
	 *
	 */
	public enum MovieStatus implements Serializable{
		MS_ComingSoon, 
		MS_Preview,
		MS_NowShowing,
		MS_EndOfShowing
	}
	/**
	 * Enumeration of cinema class
	 * @author lijiaqian
	 *
	 */
	public enum CinemaClass implements Serializable {
		CC_Gold,
		CC_Silver,
		CC_Platinum
	}
	/**
	 * Enumeration of days of the week
	 * @author 
	 *
	 */
	public enum Week implements Serializable {
		WK_Sunday,  //sunday'index is 0
		WK_Monday,
		WK_Tuesday,
		WK_Wednesday,
		WK_Thursday,
		WK_Friday,
		WK_Saturday
	}
	/**
	 * 
	 * The following methods are used to display a list of each enumeration, 
	 * including the ordinal number (starting at 0) and name of each item, 
	 * as well as a method to convert an integer to an enumeration type 
	 * (to convert an integer entered by the user to its corresponding enumeration value) 
	 */
	//MovieStatus 
	public static void showMovieStatusList() {
		int i,n;		
		MovieStatus[] ms=MovieStatus.values();	
		System.out.print("MovieStatus list is : ");
		n=ms.length;
		for(i=1;i<=n;i++) {
			System.out.print(ms[i-1].ordinal()+":"+ms[i-1].name()+" ");
		}
		System.out.println();
	}	
	public static MovieStatus intToMovieStatus(int i) {
		return MovieStatus.values()[i];
	}
	//MovieType
	public static void showMovieTypeList() {
		int i,n;		
		MovieType[] mt=MovieType.values();	
		System.out.print("MovieType list is : ");
		n=mt.length;
		for(i=1;i<=n;i++) {
			System.out.print(mt[i-1].ordinal()+":"+mt[i-1].name()+" ");
		}
		System.out.println();
	}	
	public static MovieType intToMovieType(int i) {
		return MovieType.values()[i];
	}
	//Movie Rating
	public static void showMovieRatingList() {
		int i,n;
		MovieRating[] ra=MovieRating.values();
		System.out.print("MovieRating list is : ");
		n=ra.length;
		for(i=1;i<=n;i++) {
			System.out.print(ra[i-1].ordinal()+":"+ra[i-1].name()+" ");
		}
		System.out.println();
	}
	public static MovieRating intToMovieRating(int i) {
		return MovieRating.values()[i];
	}
	//CinemaClass
	public static void showCinemaClassList() {
		int i,n;		
		CinemaClass[] cc=CinemaClass.values();		
		n=cc.length;
		System.out.print("CinemaClass list is : ");
		for(i=1;i<=n;i++) {
			System.out.print(cc[i-1].ordinal()+":"+cc[i-1].name()+" ");
		}
		System.out.println();
	}	
	public static CinemaClass intToCinemaClass(int i) {
		return CinemaClass.values()[i];
	}
	//Week
	public static void showWeekList() {
		int i,n;		
		Week[] wk=Week.values();		
		n=wk.length;
		System.out.print("Week list is : ");
		for(i=1;i<=n;i++) {
			System.out.print(wk[i-1].ordinal()+":"+wk[i-1].name()+" ");
		}
		System.out.println();
	}	
	public static Week intToWeek(int i) {
		return Week.values()[i];
	}
	

}