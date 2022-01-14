
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (Huiyi Chen) 
 * @version (13/01/2022)
 */

import java.util.*;
public class MovieRunnerAverage {

    public void printAverageRatings(){
        //SecondRatings secondRatings = new SecondRatings ("data/ratedmovies_short.csv", "data/ratings_short.csv");
        SecondRatings secondRatings = new SecondRatings ("data/ratedmoviesfull.csv", "data/ratings.csv");
        
        System.out.println("Total number of movies : " + secondRatings.getMovieSize());
        System.out.println("Total number of raters : " + secondRatings.getRaterSize());
        
        //int minimalRaters=50;  
        int minimalRaters=20;
        //int minimalRaters=12;
        
        
        ArrayList<Rating> myRatings = secondRatings.getAverageRatings(minimalRaters);
        
        Collections.sort(myRatings);
        
        System.out.println("print the list of movies, one movie per line (print its rating first, followed by its title)");
        for(Rating rating:myRatings){
            System.out.println(rating.getValue () +" : "+secondRatings.getTitle(rating.getItem ()));
            
        }
        System.out.println("There are " + myRatings.size() + " movies with " +minimalRaters + " or more ratings");
    }
    
    public void getAverageRatingOneMovie(){
        //SecondRatings secondRatings = new SecondRatings ("data/ratedmovies_short.csv", "data/ratings_short.csv");
        SecondRatings secondRatings = new SecondRatings ("data/ratedmoviesfull.csv", "data/ratings.csv");
        
        System.out.println("Total number of movies : " + secondRatings.getMovieSize());
        System.out.println("Total number of raters : " + secondRatings.getRaterSize());        
        
        //String movieTitle = "The Godfather";
        //String movieTitle = "No Country for Old Men"; //8.5
        //String movieTitle = "Inside Llewyn Davis"; //6.769230769230769
        //String movieTitle = "The Maze Runner";  //7.1875
        //String movieTitle = "Moneyball";   //8.375
        String movieTitle = "Vacation";  //6.8
        
        String movieID = secondRatings.getID(movieTitle);
        
        int minimalRaters=1;
        
        ArrayList<Rating> myRatings = secondRatings.getAverageRatings(minimalRaters);
        
        Collections.sort(myRatings);
        for(Rating rating:myRatings){
            if(rating.getItem().equals(movieID)){
                System.out.println("print average ratings for " + movieTitle + " is " + rating.getValue());
            }
        }
        
 
    }
}
