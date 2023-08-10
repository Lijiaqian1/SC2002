package MOBLIMA;
import java.io.*;
import java.util.*;

/**
 * Cinema Class
 * The cinema contains seating layout
 * @author lijiaqian
 *
 */
public class Cinema  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -204389463125353664L;
	//The multiplex where the cinema is located
	private Cineplex parentCineplex;
	//code of cinema
	private String cinemaCode;
	//SeatList of cinema
	private Seat[][] Seatarr = new Seat[0][0];
	//private String[][] s;
	private MyEnum.CinemaClass cinemaClass; //Gold, Silver, Platinum;
	//An empty array for addcinema?

	public Cinema(Cineplex parentCineplex,String cinemaCode, MyEnum.CinemaClass cinemaClass) {
	    this.parentCineplex=parentCineplex;
		this.cinemaCode=cinemaCode;
	    this.cinemaClass=cinemaClass;
	    //This is for seat;
	}
	public void showCinemaInfo() {
		System.out.println("Code : "+this.cinemaCode);
		System.out.println("CinemaClass : "+this.cinemaClass);
		System.out.println("Cineplex : "+this.parentCineplex.getCineplexName());
	}
	public Cineplex getParentCineplex() {
		return this.parentCineplex;
	}
	public void setParentCineplex(Cineplex value) {
		this.parentCineplex=value;
	}
	public String getCinemaCode() {
		return this.cinemaCode;
	}
	public MyEnum.CinemaClass getCinemaClass() {
		return this.cinemaClass;
	}
	
	public Seat[][] getSeatarr(){
		return Seatarr;
	}
	
	/**
	 * load seat list from datafile(txt)
	 * @return Seat[][],seat list
	 */
	public Seat[][] loadSeatsFromFile() {
		  String filename;
		  //read a filename,and check if file exist 
		  while(true) {
			  filename=MyFuns.getInput_str("please input the filename you are reading:  ", false);

			  File f=new File(filename);
			  if(!f.exists()) {
				  System.out.println("The file is not exist");
				  continue;
			  }
			  else
				  break;
		  }
		  
		  String[][] s;
		  s=DataFileUtil.loadDataFromTxtFile(filename);
		  Seatarr=Arrays.copyOf(Seatarr,s.length);
		  for(int i=0;i<s.length;i++) {
			  Seatarr[i]=new Seat[0];
			  Seatarr[i]=Arrays.copyOf(Seatarr[i],s[i].length);
			  for(int j=0;j<s[i].length;j++) {
				  Seatarr[i][j]=new Seat(this,null,"00",'F');
				  
				  if(s[i][j].equals("")) {
					  Seatarr[i][j].setIsSeat('F');//nothing
				  }else if(s[i][j].charAt(0)=='S') {
					  Seatarr[i][j].setIsSeat('S');//Seat
					  Seatarr[i][j].setSeatCode(s[i][j].substring(1));
				  }else if(s[i][j].charAt(0)=='T') {
					  Seatarr[i][j].setIsSeat('T');//Stair
				  }else if(s[i][j].charAt(0)=='A') {
					  Seatarr[i][j].setIsSeat('A');//Aisle
				  }
				  
			  }
			  
		  }
		  return Seatarr;
	  }
	

	public void printSeatLayout() {
		MyFuns.printSingleLine();
		System.out.println("\t\t<====== Screen =====>");
		for(int i=0;i<Seatarr.length;i++) {
			for(int j=0;j<Seatarr[i].length;j++) {
				if(Seatarr[i][j].getisSeat()=='F') {
					System.out.printf("x\t");
				}else if(Seatarr[i][j].getisSeat()=='T') {
					System.out.printf("Stair\t");
				}else if(Seatarr[i][j].getisSeat()=='A') {
					System.out.printf("Aisle\t");
				}else if(Seatarr[i][j].getisSeat()=='S') {
					System.out.printf(Seatarr[i][j].getseatCode()+"\t");
				}
				
			}
			System.out.printf("\n");
		}
		MyFuns.printSingleLine();
	}

}
