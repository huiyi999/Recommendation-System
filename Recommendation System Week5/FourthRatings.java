
/**
 * Write a description of FourthRatings here.
 * 
 * @author (Huiyi Chen) 
 * @version (13/01/2022)
 */
import java.util.*;
public class FourthRatings {
    public FourthRatings () {
        // default constructor
        this("ratings.csv");
    }  
    
    public FourthRatings (String ratingsfile) {
        RaterDatabase.initialize(ratingsfile);
    }
    
    private double getAverageByID(String id, int minimalRaters){  //id: movieID
        double sum = 0.0;
        int count = 0;
        
        for(Rater ra:RaterDatabase.getRaters()){
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
            double avgRating=Math.round(getAverageByID(movieID,minimalRaters)*100.0)/100.0;
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
            double avgRating=Math.round(getAverageByID(movieID,minimalRaters)*100.0)/100.0;
            if(avgRating!=0.0){
                Rating rating = new Rating(movieID,avgRating);
                avgRatings.add(rating);
            }
        }
        return avgRatings;       
        
        
    }
    
    //This method should first translate a rating from the scale 0 to 10 to the scale -5 to 5 and 
    // return the dot product of the ratings of movies that they both rated. 
    //This method will be called by getSimilarities.
    private double dotProduct(Rater me, Rater r){
        double dotProduct = 0.0;
        
        ArrayList<String> itemsRatedMe = me.getItemsRated();
        for(String item: itemsRatedMe){
            if(r.getItemsRated().contains(item)){
                double ratingMe = me.getRating(item);
                double ratingR = r.getRating(item);
                
                dotProduct+=(ratingMe - 5.0)*(ratingR - 5.0);
            }
        }
        return dotProduct;
    }
    
    
    //Note that in each Rating object the item field is a rater’s ID, 
    // and the value field is the dot product comparison between that rater 
    // and the rater whose ID is the parameter to getSimilarities.
    private ArrayList<Rating> getSimilarities(String id){
        ArrayList<Rating> similarities = new ArrayList<Rating> ();
        Rater me = RaterDatabase.getRater(id);
        
        for(Rater r: RaterDatabase.getRaters() ){
            if(!r.getID().equals(id)){
                double dotProduct=dotProduct(me,r);
                if(dotProduct>=0){
                    similarities.add(new Rating(r.getID(),dotProduct));
                }
            }
            
        }
        Collections.sort(similarities, Collections.reverseOrder());
        return similarities;
    }
    
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters){
        return getSimilarRatingsByFilter(id,numSimilarRaters,minimalRaters,new TrueFilter());
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter( String id, int numSimilarRaters, int minimalRaters,Filter filterCriteria){
        ArrayList<Rating> simRatings = new ArrayList<Rating> ();
        ArrayList<Rating> similarities = getSimilarities(id);
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        
        HashMap<String,Double> movieRating = new HashMap<String,Double>();
        HashMap<String,Integer> movieCount = new HashMap<String,Integer>();
        
        for(String movieID:movies){
            double rating = 0.0;
            int count = 0;
            
            for(int i=0;i<numSimilarRaters;i++){
                Rating r = similarities.get(i);
                String raterID = r.getItem();
                double  similarityRating = r.getValue();
                
                Rater rater = RaterDatabase.getRater(raterID);
                if(rater.hasRating(movieID)){
                    double tmpRating = Math.round(rater.getRating(movieID) * similarityRating*100.0) / 100.0;
                    count+=1;
                    rating+=tmpRating;
                }
            }
            
            if(count>=minimalRaters){
                movieRating.put(movieID, rating);
                movieCount.put(movieID, count);
            }
        }
        
        for(String movieID:movieRating.keySet()){
            double weightedAvgRating = Math.round((movieRating.get(movieID) / movieCount.get(movieID)) * 100.0) / 100.0;
            simRatings.add(new Rating(movieID,weightedAvgRating));
        }

        Collections.sort(simRatings, Collections.reverseOrder());
        return simRatings;     
    
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
