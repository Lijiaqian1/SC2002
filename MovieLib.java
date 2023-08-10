package MOBLIMA;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * MovieLib Class
 * The movieLib class includes a list of all the movies
 * movies can be created,deleted or updated here
 * @author lijiaqian
 *
 */
public class MovieLib implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6697110736932508719L;
	
	private Movie[] movies;
	public MovieLib() {
		this.movies=new Movie[0];
	}
	private void addMovie(Movie movie) {
		int n;
		n=movies.length+1;
		movies=Arrays.copyOf(movies, n);
		movies[n-1]=movie;
	}
	/**
	 * show the movies of which end of showing time is after current time
	 */
	public void showMoviesBeOn() {
		int flag=0;
		System.out.println("Movies being on Information is as below:");
		MyFuns.printSingleLine();
		if(movies.length==0) {
			System.out.println("NA");
		}
		for(int i=0;i<movies.length;i++) {
			if(movies[i].getEndOfShowingTime().before(Calendar.getInstance().getTime())) {
				continue;
			}else {
				movies[i].showMovieInfo();
				flag=1;
			}
						
			MyFuns.printSingleLine();
		}
		if(flag==0) {
			System.out.println("No movies are on show now");
		}
	}
	/**
	 * show all the movies that were or are on show or will be coming soon (which have been created and added by staff)
	 */
	public void showMovies() {
		System.out.println("Movies Information is as below:");
		MyFuns.printSingleLine();
		if(movies.length==0) {
			System.out.println("NA");
		}
		for(int i=0;i<movies.length;i++) {
			movies[i].showMovieInfo();			
			MyFuns.printSingleLine();
		}
	}
	public Session findSessionByID(int movieID,int SessionID) {
		for(int i=0;i<movies.length;i++) {
			if(movies[i].getMovieID()==movieID) {
				return movies[i].getSession(SessionID);
			}
		}
		return null;
	}
	/**
	 * customers can select a movie that is in preview or is on show
	 * @param allownull
	 * @return
	 */
	public Movie selectAMovie(boolean allownull) {
		int movieID;
		if(movies.length==0)
			return null;

		while(true) {
			movieID=MyFuns.getInput_int("Please enter the ID of the movie : ");
			//check if movieID exist
			for(Movie mv:movies) {
				if(mv.getMovieID()==movieID&&(((mv.getMovieStatus())==MyEnum.MovieStatus.MS_Preview)||(mv.getMovieStatus()==MyEnum.MovieStatus.MS_NowShowing))) {
					return mv;
				}
			}			
			System.out.println("Movie ID does not exist or the movie selected is not on show now");
			if(allownull) {
				return null;
			}
		}
	}
	
	public Movie newMovie() {
		String movieTitle;
		String director;
		String cast;
		String synopsis;
		MyEnum.MovieType movieType;
		MyEnum.MovieStatus movieStatus;
		MyEnum.MovieRating movieRating;
		int movieID;
		
		while(true) {
			boolean exist=false;
			movieID=MyFuns.getInput_int("moveID(int) : ");
			//check if movieID exist
			for(Movie mv:movies) {
				if(mv.getMovieID()==movieID) {
					System.out.println("The movieID exist,pls re-enter movieID ! ");
					exist=true;
					continue;
				}
			}
			if (!exist)
				break;
		}
		
		movieTitle=MyFuns.getInput_str("movieTitle : ", false);
		director=MyFuns.getInput_str("director : ", false);
		//The cast must be at least two !
		cast="";
		while(cast.split(";").length<2) {
			System.out.println("The cast must be at least two ! ");
			cast=MyFuns.getInput_str("cast(separated by ';') : ", false);
		}	
		synopsis=MyFuns.getInput_str("synopsis : ", false);
		//show movietype list
		MyEnum.showMovieTypeList();
		int flag=-1;
		while(true){
			flag=MyFuns.getInput_int("input movie-type's index : ");
			if(flag<0||flag>MyEnum.MovieType.values().length) {
				System.out.println("movie-type index invalid ! ");
				continue;
			}
			movieType=MyEnum.intToMovieType(flag);
			break;
		}

		//show moviestatus list
		MyEnum.showMovieStatusList();
		while(true){
			flag=MyFuns.getInput_int("input movie-status's index : ");
			if(flag<0||flag>MyEnum.MovieStatus.values().length) {
				System.out.println("movie-Status index invalid ! ");
				continue;
			}
			movieStatus=MyEnum.intToMovieStatus(flag);
			break;
		}	
		
		// show movieRating list
		MyEnum.showMovieRatingList();
		while(true) {
			flag=MyFuns.getInput_int("input movie-Rating's index : ");
			if(flag<0||flag>MyEnum.MovieRating.values().length) {
				System.out.println("movie-rating index invalid ! ");
				continue;
			}
			movieRating=MyEnum.intToMovieRating(flag);
			break;
		}
		
		//EndShowingTime
		Calendar endOfShowingTime=Calendar.getInstance();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String endTimeStr="";
		while(true) {
			endTimeStr=MyFuns.getInput_str("EndShowingTime(yyyy-MM-dd) : ", false);
			try {
				endOfShowingTime.setTime(sdf.parse(endTimeStr));
				break;
			}
			catch(Exception e) {
				endTimeStr="";
			}
		}
		
		
		Movie movie=new Movie(movieTitle,director,cast,synopsis,movieType,movieStatus,movieID,endOfShowingTime,movieRating);
		
		//Show the new movie information and confirm whether it is correct
		MyFuns.printSingleLine();
		System.out.println("new movie info: ");
		movie.showMovieInfo();		
		//MyFuns.printSingleLine();
		
		if (MyFuns.confirmDlg("Confirm ? (Y or N)")) {
			this.addMovie(movie);
			return movie;
		}
		else 
			return null;
	}
	

	public Movie updateMovieInfo() {
		String movieTitle;
		String director;
		String cast;
		String synopsis;
		MyEnum.MovieType movieType;
		MyEnum.MovieStatus movieStatus;
		MyEnum.MovieRating movieRating;
		
		int movieID;
		Movie movie=null;
		
		while(true) {
			movieID=MyFuns.getInput_int("Please enter the ID of the movie you want to edit : ");
			//check if movieID exist
			for(Movie mv:movies) {
				if(mv.getMovieID()==movieID) {
					movie=mv;
					break;
				}
			}
			if(movie==null)
				System.out.println("Movie ID does not exist, please re-enter");
			else
				break;
		}
		
		MyFuns.printSingleLine();
		System.out.println("The selected movie information is as follows:");
		movie.showMovieInfo();
		System.out.println("Please re-enter the movie information");
		System.out.println("If you don't want to change it, press Enter\n");
		
		movieTitle=MyFuns.getInput_str("movieTitle : ", true);
		director=MyFuns.getInput_str("director : ", true);
		//The cast must be at least two !
		cast="";
		while(cast.split(";").length<2) {
			System.out.println("The cast must be at least two ! ");
			cast=MyFuns.getInput_str("cast(separated by ';') : ", true);
			if (cast.equals(""))
				break;
		}	
		
		synopsis=MyFuns.getInput_str("synopsis : ", true);
		//show movietype list
		MyEnum.showMovieTypeList();
		int flag=-1;
		while(true){
			flag=MyFuns.getInput_int("input movie-type's index : ");
			if(flag<0||flag>MyEnum.MovieType.values().length) {
				System.out.println("movie-type index invalid ! ");
				continue;
			}
			movieType=MyEnum.intToMovieType(flag);
			break;
		}

		//show moviestatus list
		MyEnum.showMovieStatusList();
		while(true){
			flag=MyFuns.getInput_int("input movie-status's index : ");
			if(flag<0||flag>MyEnum.MovieStatus.values().length) {
				System.out.println("movie-Status index invalid ! ");
				continue;
			}
			movieStatus=MyEnum.intToMovieStatus(flag);
			break;
		}	

		// show movieRating list
		MyEnum.showMovieRatingList();
		while(true) {
			flag=MyFuns.getInput_int("input movie-Rating's index : ");
			if(flag<0||flag>MyEnum.MovieRating.values().length) {
				System.out.println("movie-rating index invalid ! ");
				continue;
			}
			movieRating=MyEnum.intToMovieRating(flag);
			break;
		}
				
		//EndShowingTime
		Calendar endOfShowingTime=Calendar.getInstance();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String endTimeStr="";
		while(true) {
			endTimeStr=MyFuns.getInput_str("EndShowingTime(yyyy-MM-dd) : ", true);
			if(endTimeStr.equals("")) {
				break;
			}
			else {
				try {
					endOfShowingTime.setTime(sdf.parse(endTimeStr));
					break;
				}
				catch(Exception e) {
					endTimeStr="";
				}				
			}

		}
		
		if(!movieTitle.equals(""))
			movie.setMovieTitle(movieTitle);
		if(!cast.equals(""))
			movie.setCast(cast);
		if(!director.equals(""))
			movie.setDirector(director);
		if(!synopsis.equals(""))
			movie.setSynopsis(synopsis);
		movie.setMovieType(movieType);
		movie.setMovieStatus(movieStatus);
		movie.setMovieRating(movieRating);
		
		if(!endTimeStr.equals("")) {
			movie.setEndOfShowingTime(endOfShowingTime);
		}
		
		
		//Show the new movie information and confirm whether it is correct
		MyFuns.printSingleLine();
		System.out.println("The updated information is as follows : ");
		movie.showMovieInfo();		
		//MyFuns.printSingleLine();
		return movie;
	}
	

	public void removeMovie() {
		int movieID;
		Movie movie=null;		
		while(true) {
			movieID=MyFuns.getInput_int("Please enter the ID of the movie you want to remove : ");
			//check if movieID exist
			for(Movie mv:movies) {
				if(mv.getMovieID()==movieID) {
					movie=mv;
					break;
				}
			}
			if(movie==null)
				System.out.println("Movie ID does not exist, please re-enter");
			else
				break;
		}
		
		MyFuns.printSingleLine();
		System.out.println("The selected movie information is as follows:");
		movie.showMovieInfo();
		
		if (!MyFuns.confirmDlg("Are you sure to remove the selected movie(change the status to 'End of Showing'?(Y or N))")) {
			return;
		}

		movie.setMovieStatus(MyEnum.MovieStatus.MS_EndOfShowing);
		System.out.println("The movie [ ID:"+movie.getMovieID()+ " Name : "+movie.getMovieTitle()+" ] has been removed. ");
		MyFuns.printSingleLine();
	
		return ;
	}

	public void showTopFiveMovieByRating() {
		Movie[] mvs=new Movie[5];
		double minRating=-100;
		int ind=-1;
		
		
		for(Movie mv : movies) {
			//find a pos in five movies,null is preferred ========
			ind=-1;
			for(int i=1 ; i<=5 ; i++) {
				if(mvs[i-1]==null) {
					ind=i-1;
					minRating=-100;
				}
			}
			if(ind==-1) {
				minRating=mvs[0].getOverallRating();
				ind=0;
				
				for(int i=2 ; i<=5 ; i++) {
					if(mvs[i-1].getOverallRating()<minRating) {
						minRating=mvs[i-1].getOverallRating();
						ind=i-1;
					}
				}
			}
			//=====================================================
			
			//If the rating is greater than the minRating in the list, replace the lowest-rating movie with the movie 
			if(mv.getOverallRating()>minRating) {
				mvs[ind]=mv;
			}
		}
		//sort
		Movie tmpmv;
		for(int i=1 ; i<=5 ; i++) {
			for(int j=i+1;j<=5;j++) {
				if(mvs[j-1]==null) {
					continue;
				}
				else if(mvs[i-1]==null || mvs[i-1].getOverallRating()<mvs[j-1].getOverallRating()) {
					tmpmv=mvs[i-1];
					mvs[i-1]=mvs[j-1];
					mvs[j-1]=tmpmv;
				}
			}
		}
		//print
		System.out.println("The top five films by rating are as follows : ");
		for(int i=1 ; i<=5 ; i++) {
			System.out.print(i+" : ");
			if(mvs[i-1]==null) 
				System.out.println("NA");
			else
				System.out.println(mvs[i-1].getMovieTitle()+"\t rating : "+mvs[i-1].getOverallRatingStr());
		}
		MyFuns.printSingleLine();
	}
	
	/**
	 * list the current top 5 ranking movies by
	 *  Ticket sales (display the movie title and total sales)
	 */
	public void showTopFiveMovieBySales() {
		Movie[] mvs=new Movie[5];
		double minSoldNum=-100;
		int ind=-1;
		
		
		for(Movie mv : movies) {
			//find a pos in five movies,null is preferred ========
			ind=-1;
			for(int i=1 ; i<=5 ; i++) {
				if(mvs[i-1]==null) {
					ind=i-1;
					minSoldNum=-100;
				}
			}
			if(ind==-1) {
				minSoldNum=mvs[0].getSeatNumSold();
				ind=0;
				
				for(int i=2 ; i<=5 ; i++) {
					if(mvs[i-1].getSeatNumSold()<minSoldNum) {
						minSoldNum=mvs[i-1].getSeatNumSold();
						ind=i-1;
					}
				}
			}
			//=====================================================
			
			//If the rating is greater than the minRating in the list, replace the lowest-rating movie with the movie 
			if(mv.getSeatNumSold()>minSoldNum) {
				mvs[ind]=mv;
			}
		}
		//sort
		Movie tmpmv;
		for(int i=1 ; i<=5 ; i++) {
			for(int j=i+1;j<=5;j++) {
				if(mvs[j-1]==null) {
					continue;
				}
				else if(mvs[i-1]==null || mvs[i-1].getSeatNumSold()<mvs[j-1].getSeatNumSold()) {
					tmpmv=mvs[i-1];
					mvs[i-1]=mvs[j-1];
					mvs[j-1]=tmpmv;
				}
			}
		}
		//print
		System.out.println("The top five films by ticket sales are as follows : ");
		for(int i=1 ; i<=5 ; i++) {
			System.out.print(i+" : ");
			if(mvs[i-1]==null) 
				System.out.println("NA");
			else
				System.out.println(mvs[i-1].getMovieTitle()+"\t total sales : "+mvs[i-1].getSeatNumSold());
		}
		MyFuns.printSingleLine();
	}
}
