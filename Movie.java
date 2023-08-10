package MOBLIMA;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Movie Class
 * The Movie class includes sessions information 
 * The movie class is a member of movielib 
 * 
 * @author lijiaqian
 *
 */
public class Movie implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8957380786441505959L;
	
	private Session[] sessions;
	private String movieTitle;
	private String director;
	private String cast;
	private String synopsis;
	private MyEnum.MovieType movieType;
	private MyEnum.MovieStatus movieStatus;	
	private OverallRating overallRating;	
	private int MovieID;
	private MyEnum.MovieRating movieRating;
	
	private Calendar endOfShowingTime;
	
	public Movie(String movieTitle,String director, String cast,String synopsis,MyEnum.MovieType movieType,MyEnum.MovieStatus movieStatus,int movieID,Calendar endOfShowingTime, MyEnum.MovieRating movieRating) {
		this.sessions=new Session[0];
		this.movieTitle=movieTitle;
		this.director=director;
		this.cast=cast;
		this.synopsis=synopsis;
		this.movieType=movieType;
		this.movieStatus=movieStatus;
		this.overallRating=new OverallRating(this);
		this.MovieID=movieID;
		this.movieRating=movieRating;
		
		this.endOfShowingTime=endOfShowingTime;
	}
	
	public void showMovieInfo() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		
		System.out.println(
				"Movie ID : "+MovieID+
				"\t Movie Overall rating : "+getOverallRatingStr()+
				"\t EndOfShowingTime : "+(endOfShowingTime==null?"":sdf.format(endOfShowingTime.getTime())));
		
		System.out.println("Movie name : "+movieTitle+"\t Movie Rating : "+movieRating);
		System.out.println("Director : "+director+"\t Cast : "+cast);
		System.out.println("Synopsis : "+synopsis);
		System.out.println("Movie type : "+movieType+"\t Movie Status : "+movieStatus);
		System.out.println("Past rating and review : ");
		this.showReview();
	}
	
	public void setEndOfShowingTime(Calendar tm) {
		this.endOfShowingTime=tm;
	}

	public void setMovieTitle(String value) {
		this.movieTitle=value;
	}

	public void setCast(String value) {
		this.cast=value;
	}

	public void setDirector(String value) {
		this.director=value;
	}

	public void setSynopsis(String value) {
		this.synopsis=value;
	}
	
	public void setMovieType(MyEnum.MovieType value) {
		this.movieType=value;
	}
	public void setMovieStatus(MyEnum.MovieStatus value) {
		this.movieStatus=value;
	}
	public void setMovieRating(MyEnum.MovieRating value) {
		this.movieRating=value;
	}
	
	
	
	public Calendar getEndOfShowingTime() {
		return this.endOfShowingTime;
	}
	
	public int getMovieID() {
		return this.MovieID;
	}

	public String getMovieTitle() {
		return this.movieTitle;
	}
	
	public MyEnum.MovieType getMovieType(){
		return this.movieType;
	}
	
	public MyEnum.MovieStatus getMovieStatus(){
		return this.movieStatus;
	}
	public MyEnum.MovieRating getMovieRating() {
		return this.movieRating;
	}
	


	public Session getSession(int sessionID) {
		for(int i=0;i<sessions.length;i++) {
			if(sessions[i].getsessionID()==sessionID) {
				return sessions[i];
			}
		}
		return null;
	}
			
	public Session newSession(
				Cinema cinema,
				Calendar showtime) {
		//Automatically generated sessionID
		int sessionID;
		sessionID=0;
		for(Session sn:sessions) {
			if(sn.getsessionID()>sessionID)
				sessionID=sn.getsessionID();
		}
		sessionID++;
		
		Session session=new Session(this,cinema,showtime,sessionID);		
		this.addSession(session);
		
		return session;
	}
	
	private void addSession(Session session) {
		session.setParentMovie(this);
		int n;
		n=sessions.length+1;
		sessions=Arrays.copyOf(sessions, n);
		sessions[n-1]=session;
	}
	public void deleteSession(Session session) {
		int flag=0;
		for(int i=0;i<sessions.length;i++) {
			if(sessions[i]==session) {
				flag=1;
				continue;
			}
			if(flag==1) {
				sessions[i-1]=sessions[i];
			}
		}
		int n;
		n=sessions.length-1;
		sessions=Arrays.copyOf(sessions, n);
	}
	
	public Session selectASession() {
		int sessionID;
		
		if (sessions.length==0){
			return null;
		}
		//Displays the session list for selection 
		showSessions();
		while(true) {
			sessionID=MyFuns.getInput_int("Please enter the session ID : ");
			for(Session sn:sessions) {
				if(sn.getsessionID()==sessionID) {
					return sn;
				}
			}
			System.out.println("SessionID does not exist ");
			
		}

	}
	
	public void removeSession(Session sn) {
		int flag=0;
		for(int i=0;i<sessions.length;i++) {
			
			if(flag==0 && sessions[i]==sn) {
				flag=1;
				continue;
			}
			
			if (flag==1)
				sessions[i-1]=sessions[i];
		}
		sessions=Arrays.copyOf(sessions, sessions.length-1);
	}
	
	public void showSessions() {
		System.out.println("Sessions information is as below:");
		if(sessions.length==0)
			System.out.println("NA");
		for(int i=0;i<sessions.length;i++) {
			sessions[i].showSession();
			MyFuns.printSingleLine();
		}
	}

	public void showReview() {
		overallRating.showReview();
	}

	public void addRating() {
		overallRating.addRating();
	}

	/*
	 * Used to display rating result
	 * The overall rating is the average (to 1 decimal place) of the all individual ratings 
	 * for the particular movie. Overall reviewer rating will only be displayed if there are 
	 * more than ONE individual rating, else “NA” is displayed
	 * 
	 * @return String : OverallRating
	 */
	public String getOverallRatingStr() {
		double rating= overallRating.getOverallRating();
		if(rating<0)
			return "NA";
		else
			return String.format("%.1f",rating);
		
	}
	/**
	 * Used to rank by rating
	 * @return double : OverallRating, 
	 */
	public double getOverallRating() {
		
		double rating= overallRating.getOverallRating();
		return Math.round(rating*10)*1.0/10;
	}
	
	/**
	 * Add up the tickets sold for each session
	 * @return 
	 */
	public int getSeatNumSold() {
		int n=0;
		for(Session sn : sessions) {
			n=n+sn.getSeatNumSelected();
		}
		return n;
	}

}
