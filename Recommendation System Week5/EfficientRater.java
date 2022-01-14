
/**
 * Write a description of EfficientRater here.
 * 
 * @author (Huiyi Chen) 
 * @version (13/01/2022)
 */
import java.util.*;
public class EfficientRater implements Rater{
    private String myID;
    private HashMap<String,Rating> myRatings; 
    // The key in the HashMap is a movie ID, 
    // its value is a rating associated with this movie.

    public EfficientRater(String id) {
        myID = id;
        myRatings = new HashMap<String,Rating>();
    }

    public void addRating(String item, double rating) {
        //myRatings.add(new Rating(item,rating));
        myRatings.put(item,new Rating(item,rating));
        
    }

    public boolean hasRating(String item) {
        //for(int k=0; k < myRatings.size(); k++){
         //   if (myRatings.get(k).getItem().equals(item)){
        //        return true;
         //   }
        //}
        
        //return false;
        return myRatings.containsKey(item);
    }

    public String getID() {
        return myID;
    }

    public double getRating(String item) {
        for (String movieID : myRatings.keySet()) {
            if (movieID.equals(item)) {
                return myRatings.get(movieID).getValue();
            }
        }
        
        return -1;
    }

    public int numRatings() {
        return myRatings.size();
    }

    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<String>();
        for(String movieID : myRatings.keySet()){
            list.add(myRatings.get(movieID).getItem());
        }
        
        return list;
    }
}
