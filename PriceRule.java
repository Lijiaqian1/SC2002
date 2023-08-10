package MOBLIMA;

import java.io.Serializable;
import java.util.Arrays;

/**
 * PriceRule class
 * this class contains how various conditions(such as movie types, customer age,etc.)affect the price
 * Final price can be calculated by the methods in this class (tax is not included yet here)
 * @author Sun mingzhong
 */
public class PriceRule  implements Serializable {
  
	/**
	 * 
	 */
	private static final long serialVersionUID = -5209286619732567861L;
	private double price;
	public PriceRule() {
		this.price=20;
	}
	private static final double priceSimple = 1.0;
	private static final double price3D = 1.2;
	private static final double priceBlockbuster =1.5;
    
	private static final double discount_senior=0.75;
	private static final double discount_student=0.8;
	private static final double discount_child=0.75;
  
	private static final double price_Platinumclass=1.2;
	private static final double price_Goldclass=1.1;
	private static final double price_Silverclass=1.0;
  
	private static final double price_WeekendPH=1.25;

	private String [] PHs=new String[] {"1-1","2-1","2-2","4-15","5-1","5-3","5-15","7-10","8-9","10-24","12-25"};
  
	/**
	* Enter the holiday information into the list
	*/
	public void InputPHs() {
	  String d=MyFuns.getInput_str("Please enter holidays in the format of \\\"MM-dd\\\" : ",false) ;

	  String[] s=d.split("-");
	  
	  if(s.length==2) {
		  PHs=Arrays.copyOf(PHs,PHs.length+1);
		  //Reassemble the string and convert the format: 10-05 to 10-5
		  PHs[PHs.length-1]=String.valueOf(Integer.parseInt(s[0]))+"-"+String.valueOf(Integer.parseInt(s[1]));
	  }
	  else
		  System.out.println("The holiday format is invalid ");	  
	  
	}
	/**
	* Clear holiday list
	*/
	public void ClearPhs() {
		PHs=new String[0];
	}
	/**
	* @author lijiaqian
	* Remove an item from the list of holidays
	*/
	public void removePHs() {

	  //showPHs();
	  int ind=MyFuns.getInput_int("Enter the index of the data you want to delete : ") ;
	  
	  if(ind>=1&&ind<=PHs.length) {
			for(int i=1;i<PHs.length;i++) {
				if(i<ind) 
					continue;
				else
					PHs[i-1]=PHs[i];
			}
			PHs=Arrays.copyOf(PHs, PHs.length-1);
	  }
	  else
		  System.out.println("The index is invalid ");
	  
	}
	/**
	 * Displaying the Holiday List
	 */
	public void showPHs() {
	  int i,n;
	  n=PHs.length;
	  for(i=1;i<=n;i++) {
		  System.out.println(i+" : "+PHs[i-1]);
	  }
	}
	/**
	 * Calculate the price of a movie ticket
	 * @param age
	 * @param cinemaClass
	 * @param movieType
	 * @param date
	 * @param week
	 * @return
	 */
	public double PriceCalculation(int age, MyEnum.CinemaClass cinemaClass, MyEnum.MovieType movieType, String date, MyEnum.Week week) {

	    if(movieType== MyEnum.MovieType.MT_3D) this.price*=price3D;
	    if(movieType==MyEnum.MovieType.MT_Blockbuster) this.price*=priceBlockbuster;
	    if(movieType==MyEnum.MovieType.MT_Simple) this.price*=priceSimple;
	    
	    if (age>=65) this.price*=discount_senior;
	    if (age<=8) this.price*=discount_child;
	    if (age>=12&&age<=22) this.price*=discount_student;
	    
	    if (cinemaClass==MyEnum.CinemaClass.CC_Platinum) this.price*=price_Platinumclass;
	    if (cinemaClass==MyEnum.CinemaClass.CC_Gold) this.price*=price_Goldclass;
	    if (cinemaClass==MyEnum.CinemaClass.CC_Silver) this.price*=price_Silverclass;
	    
	    if (week==MyEnum.Week.WK_Saturday || week==MyEnum.Week.WK_Sunday) 
	    	this.price*=price_WeekendPH;
		else {
			for(int i=0;i<PHs.length-1;i++){
				if (date.equals(PHs[i])) {
					this.price*=price_WeekendPH;
					break;
				}
			}
		}

	    return this.price;
	}
}