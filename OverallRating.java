package MOBLIMA;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 
 * OverallRating Class
 * The overallRating class contains a list of all ratings and reviews for a movie, 
 * including movie-goer names, ratings, and reviews 
 * 
 * @author lijiaqian
 *
 */
public class OverallRating  implements Serializable{
					/**
					 * 
					 */
					private static final long serialVersionUID = 4006509196111178992L;
					
					private class MovieRating implements Serializable{
						private int rating;
						private String review;
						private String moviegoer;
						private Calendar ratingtime;
						
						public MovieRating(String moviegoer,int rating,String review) {
							this.moviegoer=moviegoer;
							this.rating=rating;
							this.review=review;
							this.ratingtime=Calendar.getInstance();
						}
						public int getRating() {
							return this.rating;
						}
						public String getReview() {
							return this.review;
						}
						public String getMoviegoes() {
							return this.moviegoer;
						}
						public Calendar getRatingtime() {
							return this.ratingtime;
						}
					}
	
	private Movie parentMovie;
	private MovieRating movieRating[]= new MovieRating[0];
	
	public OverallRating(Movie parentMovie) {
		this.parentMovie=parentMovie;		
		this.movieRating=new MovieRating[0];
	}
	private boolean moviegoerRatingExist(String goer) {
		int i;
		for (i=1 ;i<=movieRating.length;i++) {
			if (movieRating[i-1].getMoviegoes().equals(goer)) {
				return true;
			}
		}
		return false;
	}
	
	public void addRating() {
		/**
		 * add rating or/and review for a movie
		 */
		String moviegoer;
		int rating;
		String review;
		
			
			moviegoer=MyFuns.getInput_str("please give your name:", false);;			
			if(moviegoer.equals("")) {
				System.out.println("Name is invalid ");
				return;
			}
			else if (moviegoerRatingExist(moviegoer)) {
				System.out.println("You have finished rating the movie");
				return;
			}		
			System.out.printf("please give the rating on the scale of 5:\n");
			rating=MyFuns.getInput_int("please give the rating:");
			if (rating<1||rating>5) {
				System.out.println("The rating must be between 1 and 5");
				return;
			}
			review=MyFuns.getInput_str("please give the review of the movie:", false);
			if (review.equals("")) {
				review="no review.";
			}
			
			int n;
			n=this.movieRating.length+1;
			this.movieRating=Arrays.copyOf(this.movieRating, n);
			this.movieRating[n-1]=new MovieRating(moviegoer,rating,review);
			
		

	}
	/*
	 * The overall rating is the average (to 1 decimal place) of the all individual ratings 
	 * for the particular movie. Overall reviewer rating will only be displayed if there are 
	 * more than ONE individual rating, else “NA” is displayed
	 */
	public double getOverallRating() {
		int n=movieRating.length;
		if (n>=1) {
			int sum=0;
			for(int i=0;i<n;i++) {
				sum+=movieRating[i].getRating();
			}
			double ave;
			ave=sum*1.0/n;
			return ave;
		}
		else
			return -1;
	}
	public void showReview() {
		/**
		 * print reviews of the movie
		 */
		System.out.println("Reviews of "+this.parentMovie.getMovieTitle());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		for(int i=0;i<movieRating.length;i++) {
			//rating time and reviewer's name
			System.out.println(sdf.format(movieRating[i].getRatingtime().getTime())+"\t"+movieRating[i].getMoviegoes());
			//review
			System.out.println("Review:");
			System.out.println(movieRating[i].getReview());
			System.out.println("Rating:");
			System.out.println(movieRating[i].getRating());
		}
		
	}
}
