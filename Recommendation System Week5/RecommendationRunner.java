
/**
 * Write a description of RecommendationRunner here.
 * 
 * @author (Huiyi Chen) 
 * @version (13/01/2022)
 */

import java.util.*;
public class RecommendationRunner implements Recommender{
// It returns a list of strings representing movie IDs 
// that will be used to present movies to the user for them to rate. 

// You can choose how to select movies for this list, for example, 
// you could select recent movies, movies from a specific genre, randomly chosen movies, or something else. 
 
// 10-20 movies should be fine to get a good profile of the user’s opinions, but 50 may be too many.    
    public ArrayList<String> getItemsToRate (){
        ArrayList<String> itemsToRate = new ArrayList<String> ();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());

        HashMap<String,Integer> moviesRatings = new HashMap<String,Integer>();
        for(String id :movies){
            for(Rater ra:RaterDatabase.getRaters()){
                if(ra.hasRating(id)){
                    if(!moviesRatings.containsKey(id)){
                        moviesRatings.put(id,1);
                    }else{
                        int tmp = moviesRatings.get(id);
                        moviesRatings.put(id,tmp+1);
                    }
                }
            }

        }
 
        
        for (int i=0; i < 15; i++) {
            Random random = new Random();
            int r = random.nextInt(movies.size());
            if (! itemsToRate.contains(movies.get(r))) {
                itemsToRate.add(movies.get(r));
            }
        }
        
        
        
        //System.out.println(itemsToRate.size());
        return itemsToRate;        
        
    }
    
// It prints out an HTML table of movies recommended by your program for the user based on the movies they rated. 

// To get the movies recommended by your program, you may want to use your FourthRatings class. 
 
// If no movies are recommended, you should notify the user with a message. 

// Whatever is printed by this method will be displayed on the web page: HTML, plain text, or debugging information. 
// If you want to style this HTML page, please include the CSS styling directly 
// within the page using the <style> tag.   
    public void printRecommendationsFor (String webRaterID){  //ID of the user
        FourthRatings fourthRatings = new FourthRatings ();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");        
        
        System.out.println("<p>Read data for " + Integer.toString(RaterDatabase.size()) + " raters</p>");
        System.out.println("<p>Read data for " + Integer.toString(MovieDatabase.size()) + " movies</p>"); 
        
        int numSimilarRaters = 50; 
        int minNumOfRaters = 3; 
        ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatings(webRaterID, numSimilarRaters, minNumOfRaters);
        
        if (similarRatings.size() == 0) {
            System.out.println("No movies were found");
        } else {
            String header = ("<table> <tr> <th>Movie Title</th> <th>Rating Value</th>  <th>Genres</th> </tr>");
            String body = "";
            for (Rating rating : similarRatings) {
                body += "<tr> <td>" + MovieDatabase.getTitle(rating.getItem()) + "</td> <td>" 
                + Double.toString(rating.getValue()) + "</td> <td>" + MovieDatabase.getGenres(rating.getItem())
                + "</td> </tr> ";
            }
            System.out.println(header  + body + "</table>");
            System.out.println(similarRatings.size()+" movies were found");
        }    
    }
}
