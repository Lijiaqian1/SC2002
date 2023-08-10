package MOBLIMA;
import java.io.Serializable;


/**
 * The seat class is a member of the cinema and a member of the session,
 * When the user creates a session, copy the layout(seats) from cinema into the session 
 * 
 * @author Lijiaqian
 *
 */
public class Seat implements Serializable{

	private static final long serialVersionUID = 467918497281946141L;
	
	/**
	 * attributes of Seat 
	 */
	private Cinema parentCinema;
	private Session parentSession;
	private String seatCode;
	private char isSeat;
	private Boolean isSelected=false;
	private String tid;
   
   /**
    * 
    * @param parentCinema
    * @param seatCode
    * @param isSeat
    */
	public Seat(Cinema parentCinema,Session parentSession,String seatCode,char isSeat){
		this.parentCinema=parentCinema;
		this.parentSession=parentSession;
		this.seatCode=seatCode;
		this.isSeat=isSeat;
		this.isSelected=false;
		this.tid="";
	}
	public void bookSeat(String tid) {
		this.isSelected=true;
		this.tid=tid;
	}
	/**
	 * 
	 * @param isSeat:'S':Seat,'T':Stair,'A':Aisle,else 'F'
	 */
	public void setIsSeat(char isSeat) {
		this.isSeat=isSeat;
	}
	public void setSeatCode(String seatCode) {
		this.seatCode=seatCode;
	}
	public void setTID(String tid) {
		this.tid=tid;
	}
	public String getTID() {
		return this.tid;
	}
	
	public String getseatCode() {
		return this.seatCode;
	}
	public char getisSeat() {
		return this.isSeat;
	}
	public Boolean getisSelected() {
		return this.isSelected;
	}
	public Cinema getparentCinema() {
		return this.parentCinema;
	}
	public Session getparentSession() {
		return this.parentSession;
	}
}
