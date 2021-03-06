
/**
 * Write a description of class Rater here.
 * 
 * @author (Huiyi Chen) 
 * @version (12/01/2022)
 */

import java.util.*;

public interface Rater {

    public void addRating(String item, double rating);

    public boolean hasRating(String item);

    public String getID() ;

    public double getRating(String item) ;

    public int numRatings() ;

    public ArrayList<String> getItemsRated() ;
}
