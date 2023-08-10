package MOBLIMA;
import java.io.Serializable;
import java.util.Arrays;

/**
 * The TicketLib class includes a list of all the tickets
 * @author Sun mingzhong, Li jiaqian
 *
 */
public class TicketLib implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6044739490673593129L;
	
	private Ticket[] tickets;
	public TicketLib() {
		this.tickets=new Ticket[0];
	}
    private void addTicket(Ticket Ticket) {
        int n;
        n=tickets.length+1;
        tickets=Arrays.copyOf(tickets, n);
        tickets[n-1]=Ticket;
        
        
      }
  
	public void showTicketsInfo() {
        System.out.println("Tickets Information is as below:");
        for(int i=0;i<tickets.length;i++) {
          tickets[i].printTicketInfo();
          System.out.println("------------------------------------");
          }
	}
	public Ticket createTicket (String CustomerName,String mobileNumber, String Email, int age, Session session,String SeatCode ) {
	   Ticket ticket=new Ticket(CustomerName,mobileNumber, Email, age, session,SeatCode );
	   ticket.showTicketPrice();
	   boolean confirm=MyFuns.confirmDlg("please confirm your payment(Y for confirmation,N for cancellation)");
	   if(confirm==true) {
		   this.addTicket(ticket);   
		   session.bookSelectedSeatOfSession(SeatCode,ticket.getTID());
		   return ticket;
	   }else {
		   return null;
	   }
	   
	   
	   
	}
	/**
	 * customer can search their buying history by his or her name in the list of existing tickets
	 */
	public void viewhistory() {
		String CustomerName;
		CustomerName=MyFuns.getInput_str("please input your name: ",false);
	   for(int i=0;i<tickets.length;i++) {
		   if(tickets[i].getCustomerName().equals(CustomerName)) {
			   tickets[i].printTicketInfo();
			   System.out.println("-------------------------------------------");
		   }
	   }
	}
	/**
	 * Sales of a certain movie can be obtained by traversing the list of tickets and recording the tickets of a certain movie
	 * @param movieName
	 * @return
	 */
	public int getSales(String movieName) {
	   int sales=0;
	   for(int i=0;i<tickets.length;i++) {
		   if(tickets[i].getMovieName().equals(movieName)) {
			   sales++;
		   }
	   }
	   return sales;
	}
   
}