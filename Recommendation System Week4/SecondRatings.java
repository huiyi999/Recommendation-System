
/**
 * Write a description of SecondRatings here.
 * 
 * @author (Huiyi Chen) 
 * @version (13/01/2022)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("data/ratedmoviesfull.csv", "data/ratings.csv");
    }
    
    public SecondRatings (String moviefile, String ratingsfile) {
        FirstRatings firstRatings = new FirstRatings ();
        myMovies = firstRatings.loadMovies(moviefile);
        myRaters = firstRatings.loadRaters(ratingsfile);
    } 
    
    public int getMovieSize(){
        return myMovies.size();
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
        ArrayList<Rating> myRatings = new ArrayList<Rating> ();;
        for(Movie m :myMovies){
            String movieID= m.getID();
            double avgRating=getAverageByID(movieID,minimalRaters);
            if(avgRating!=0.0){
                Rating rating = new Rating(movieID,avgRating);
                myRatings.add(rating);
            }
        }
        return myRatings;
    }
    
    public String getTitle(String id){
        String title=null;
        for(Movie m :myMovies){
            if(m.getID().equals(id)){
                title=m.getTitle();
            }
        }
        
        if(title!=null){
            return title;
        }
        else{
            return "No movie with such ID was found.";
        }
    }
    
    public String getID(String title){
        String id=null;
        for(Movie m :myMovies){
            if(m.getTitle().equals(title)){
                id=m.getID();
            }
        }
        
        if(id!=null){
            return id;
        }
        else{
            return "NO SUCH TITLE.";
        }
    }
    
}