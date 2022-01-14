
/**
 * Write a description of ThirdRatings here.
 * 
 * @author (Huiyi Chen) 
 * @version (13/01/2022)
 */

import java.util.*;
public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this( "data/ratings.csv");
    }
    
    public ThirdRatings (String ratingsfile) {
        FirstRatings firstRatings = new FirstRatings ();
        myRaters = firstRatings.loadRaters(ratingsfile);
    } 
    
    
    public int getRaterSize(){
        return myRaters.size();
    }
    
    private double getAverageByID(String id, int minimalRaters){  //id: movieID
        double sum = 0.0;
        int count = 0;
        
        for(Rater ra:myRaters){
            if(ra.hasRating(id)){
                count+=1;
                sum+=ra.getRating(id);
            }
        }

        if(count>=minimalRaters){
            return sum/count;
            
        }else{
            return 0.0;
        }
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<Rating> myRatings = new ArrayList<Rating> ();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for(String movieID :movies){
            double avgRating=getAverageByID(movieID,minimalRaters);
            if(avgRating!=0.0){
                Rating rating = new Rating(movieID,avgRating);
                myRatings.add(rating);
            }
        }
        return myRatings;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters,Filter filterCriteria){
        ArrayList<Rating> avgRatings = new ArrayList<Rating> ();
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        for(String movieID :movies){
            double avgRating=getAverageByID(movieID,minimalRaters);
            if(avgRating!=0.0){
                Rating rating = new Rating(movieID,avgRating);
                avgRatings.add(rating);
            }
        }
        return avgRatings;       
        
        
    }
    
   
}
