package MOBLIMA;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Cineplex Class
 * A cineplex contains multiple cinemas
 * new cinemas can be created here
 * @author lijiaqian
 *
 */
public class Cineplex implements Serializable {
  /**
   * 
   */
  private static final long serialVersionUID = 3424545383580327839L;
  
  private Cinema[] cinemas;//MIN_CINEMA is 3
  private String CineplexCode;
  private String CineplexName;
  private String Location;
  
  public Cineplex(String CineplexCode,String CineplexName,String Location) {
	  this.cinemas=new Cinema[0];  
	  this.CineplexCode=CineplexCode;
	  this.CineplexName=CineplexName;
	  this.Location=Location;
  }
  public void addCinema(Cinema cinema) {
	int n;
	//set cinema's parentCineplex
	cinema.setParentCineplex(this);
	
	n=cinemas.length+1;
	cinemas=Arrays.copyOf(cinemas, n);
	cinemas[n-1]=cinema;
  }
  
  public Cinema newCinema() {
	  String cncode;
	  MyEnum.CinemaClass cncls;
	  
	  while(true) {
		  cncode=MyFuns.getInput_str("Please enter the cinema code : ", false);
		  for(Cinema cn:cinemas) {
			  if(cn.getCinemaCode().equals(cncode)) {
				  System.out.println("The cinema number already exists ");
				  cncode="";
			  }
		  }
		  if(!cncode.equals(""))
			  break;
	  }
	  MyEnum.showCinemaClassList();
	  int n=MyFuns.getInput_int("Please enter the cinema class : ");
	  if(n<0||n>MyEnum.CinemaClass.values().length) {
		  System.out.println("index of cinema-class invalid,cann't continue");
		  return null;
	  }
	  cncls=MyEnum.intToCinemaClass(n);
	  Cinema cn=new Cinema(this,cncode,cncls);
	  MyFuns.printSingleLine();
	  cn.showCinemaInfo();
	  
	  if(MyFuns.confirmDlg("Confirm ?(Y or N)")){
		  addCinema(cn);		  
		  return cn;
	  }
	  else
		  return null;
  }
  
  public void printCineplexInfo() {
	  System.out.println("Code : "+CineplexCode);
	  System.out.println("Name : "+CineplexName);
	  System.out.println("Location : "+Location);
  }
  public String getCineplexCode() {
	  return this.CineplexCode;
  }
  
  public String getCineplexName() {
	  return this.CineplexName;
  }
  
  public void showCinemaList() {
	  if(cinemas.length==0) {
		  System.out.println("NA");
	  }
	  
	  for(Cinema cn:cinemas) {
		  cn.showCinemaInfo();
	  }
  }
  
  public Cinema selectACinema() {
	  String cinemaCode;
	  if (cinemas.length==0)
		  return null;
	  
	  while(true) {
		  
		  cinemaCode=MyFuns.getInput_str("Please enter the cinema code : ", false);
		  for(Cinema cn:cinemas) {
			  if(cn.getCinemaCode().equals(cinemaCode)) {
				  return cn;
			  }
		  }
		  System.out.println("Cinema number does not exist.");
	  }
  }
}