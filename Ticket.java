package MOBLIMA;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Ticket information of a certain customer for a certain session of a movie
 * Ticket class is a member of TicketLib
 * @author Sun mingzhong, Li Jiaqian
 *
 */
public class Ticket implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2516649988250181807L;

	private Session parentSession;
  
	private String tid;
	private String CustomerName;
	

	
	private String mobileNumber;
	private String Email;
	private double price;
	private String SeatCode;
	private int age;
  
	
	private PriceRule pricerule=new PriceRule();
	private static final double taxrate=0.07;
	/**
	* 
	* @param CustomerName
	* @param mobileNumber
	* @param Email
	* @param age
	* @param session
	* @param SeatCode
	*/
	public Ticket(String CustomerName,String mobileNumber, String Email, int age, Session session,String SeatCode )
	{  
		this.parentSession=session;
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
		Calendar c=Calendar.getInstance();
		
	    this.tid=session.getParentCinema().getCinemaCode()+sdf.format(c.getTime());
	    
	    this.CustomerName=CustomerName;
	  
	    
	    this.mobileNumber=mobileNumber;
	    this.Email=Email;
	    this.price=pricerule.PriceCalculation(
	    		age,
	    		session.getParentCinema().getCinemaClass(),
	    		session.getParentMovie().getMovieType(),
	    		session.getDate(),
	    		session.getWeek());
	
	    this.SeatCode=SeatCode;
	    this.age=age;

	}
	
	public String getTID() {
		return this.tid;
	}
  
  public String getCustomerName() {
	  return this.CustomerName;
  }
  public String getMovieName() {
	  return this.parentSession.getParentMovie().getMovieTitle();
  }

  public double getTicketPrice() {
	  return this.price;
  }
  /**
   * Final ticket price with tax inclusive is shown by this method
   */
  public void showTicketPrice() {
	  System.out.println("Ticket price is SGD $"+price);
	  System.out.println("Tax is SGD $"+price*taxrate);
	  System.out.println("Total price(tax inclusive) is SGD $"+(price+price*taxrate));
  }
  
    public void printTicketInfo() {
    	System.out.println("Cineplex is "+parentSession.getParentCinema().getParentCineplex().getCineplexName());
    	System.out.println("Cinema Code is "+parentSession.getParentCinema().getCinemaCode());
    	System.out.println("Cinema Class is "+parentSession.getParentCinema().getCinemaClass());
        System.out.println("transaction ID is "+tid);
        System.out.println("Customer Name is "+CustomerName);
        System.out.println("Customer Number is "+mobileNumber);
        System.out.println("Customer Email is "+Email);
        System.out.printf("Price is %.2f\n",price+price*taxrate);
        System.out.println("Movie Name is "+parentSession.getParentMovie().getMovieTitle());
        System.out.println("Movie Type is "+parentSession.getParentMovie().getMovieType());
        System.out.println("session ID is "+parentSession.getsessionID());
        System.out.println("seat ID is "+SeatCode);
        System.out.println("Customer age is"+age);
        
      }
}