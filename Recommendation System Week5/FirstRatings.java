import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
/**
 * Write a description of FirstRatings here.
 * 
 * @author (Huiyi Chen) 
 * @version (12/01/2022)
 */
public class FirstRatings {
  
    public ArrayList<Movie> loadMovies(String filename){
        FileResource fr = new FileResource("data/" +filename);
        ArrayList<Movie> movieList = new ArrayList<Movie>();
        for(CSVRecord record : fr.getCSVParser()){
            Movie m = new Movie(record.get("id"),record.get("title"),
            record.get("year"),record.get("genre"),
            record.get("director"),record.get("country"),
            record.get("poster"),Integer.parseInt(record.get("minutes")));;
            movieList.add(m);
        
        }
        return movieList;
    }
    
    public void testLoadMovies(){
        //String filename = "data/ratedmovies_short.csv";  //num:5
        String filename = "ratedmoviesfull.csv";  
        ArrayList<Movie> loadedMovies = loadMovies(filename);
        
        System.out.println("number of movies: "+ loadedMovies.size());
        System.out.println("loadedMovies: "+ loadedMovies);
        
        int numOfGenreCount = 0;
        int numOfGreater150Mins = 0;
        int maxMoviesByDirector=0;
        String genre = "Comedy";
        
        HashMap<String,Integer> directorMap = new HashMap();
 
        for(int k = 0; k<loadedMovies.size();k++){
            Movie cur = loadedMovies.get(k);
            if(cur.getGenres().contains(genre)){
                numOfGenreCount++; 
            }
            if(cur.getMinutes()>150){
                numOfGreater150Mins++;
            }
            
            String director = cur.getDirector();
            
            if(directorMap.containsKey(director)){
                directorMap.put(director,directorMap.get(director)+1);
            }else{
                directorMap.put(director,1);
            }
        }
        
        maxMoviesByDirector = Collections.max(directorMap.values());
        
        ArrayList<String> directorsOfmaxMovies = new ArrayList();
        for(String dir:directorMap.keySet()){
            if(directorMap.get(dir) == maxMoviesByDirector){
                directorsOfmaxMovies.add(dir);
         }
     }  
        System.out.println("Number of movies include the Comedy genre: "+ numOfGenreCount);
        System.out.println("Number of movies greater than 150 minutes in length: "+ numOfGreater150Mins);
        
        System.out.println("Maximum number of movies by any director: " + maxMoviesByDirector);
     
        System.out.println("Directors that directed the Maximum number of movies: \n" + directorsOfmaxMovies); 
    }
    
    public ArrayList<Rater> loadRaters(String filename){
        FileResource fr = new FileResource("data/" +filename);
        
        ArrayList<Rater> raterList = new ArrayList<Rater>();
        ArrayList<String> listOfIDs = new ArrayList<String> ();
        
        for(CSVRecord record:fr.getCSVParser()){
            String rater_id = record.get("rater_id");
            String movie_id = record.get("movie_id");
            double rating = Double.parseDouble(record.get("rating"));
            
            if(!listOfIDs.contains(rater_id)){
                //Rater rater = new PlainRater(rater_id);
                
                Rater rater = new EfficientRater(rater_id);
                rater.addRating(movie_id, rating);
                raterList.add(rater);
            }else{
                for(int k=0;k<raterList.size();k++){
                    if(raterList.get(k).getID().equals(rater_id)){
                        raterList.get(k).addRating(movie_id, rating);
                    }
                    
                }

            }
            listOfIDs.add(rater_id);
        }
        System.out.println(raterList.size());

        return raterList;
    }
    
    
    
    public void testLoadRaters(){
        //String filename = "data/ratings_short.csv";  
        String filename = "ratings.csv";  
        
        ArrayList<Rater> loadedRaters = loadRaters(filename);
        
        //Print the total number of raters. 
        //Then for each rater, print the rater’s ID and the number of ratings they did on one line, 
        //followed by each rating (both the movie ID and the rating given) on a separate line. 
        System.out.println("total number of raters: "+ loadedRaters.size());
        //for(Rater rater: loadedRaters){
        //    int num = rater.numRatings();
        //    System.out.println("rater: "+rater.getID()+" number of ratings: "+ num );
        //    ArrayList<String> movieList = rater.getItemsRated();
         //   for(int i =0;i<num;i++){
         //       System.out.println(movieList.get(i)+" : "+rater.getRating(movieList.get(i)));
                
         //   }
            
        //}
          
        
        // find the number of ratings for a particular rater 
        String raterID = "193";
        int numOfRatingPerRater = getRatingPerRater(loadedRaters, raterID);
        System.out.println("the number of ratings for rater_id("+ raterID + "): " + numOfRatingPerRater);

        // find the maximum number of ratings by any rater, 
        // Determine how many raters have this maximum number of ratings and who those raters are. 
        HashMap<String,Integer> ratersWithRatings = getRatingPerRater(loadedRaters, new HashMap<String,Integer>());
        
        int maxNumOfRatings = Collections.max(ratersWithRatings.values());
        System.out.println("the maximum number of ratings by rater: " + maxNumOfRatings);
        
        List<String> raterWithMax = new ArrayList<>();
        
        for(String rater : ratersWithRatings.keySet()){
            if(ratersWithRatings.get(rater) == maxNumOfRatings){
                raterWithMax.add(rater);
            }
        }
        System.out.println("raters who have the maximum number of ratings: " + Arrays.toString(raterWithMax.toArray()));
        
        // find the number of ratings a particular movie has
        //String movieID = "193";
        String movieID = "1798709";
        int numOfRatings = getNumberOfRating(loadedRaters, movieID);
        System.out.println("the number of ratings that movie" + movieID+" has: "+ numOfRatings);
        
        // determine how many different movies have been rated by all these raters
        HashMap<String,Integer> movieWithNumOfRater = getmovieWithNumOfRater(loadedRaters, new HashMap<String,Integer>());
        
        System.out.println("number of movies rated: " + movieWithNumOfRater.size());

        
    }
    
    public int getRatingPerRater(ArrayList<Rater> loadedRaters,String raterID){
        int numOfRatingPerRater=0;
        for(int k =0;k<loadedRaters.size();k++){
            Rater ra=loadedRaters.get(k);
            if(ra.getID().equals(raterID)){
                numOfRatingPerRater=ra.numRatings();
            }
            
        
        }
        return numOfRatingPerRater;
    }
    
    public HashMap<String,Integer> getRatingPerRater(ArrayList<Rater> loadedRaters, HashMap<String,Integer> ratersWithRatings){
        for(int k =0;k<loadedRaters.size();k++){
            Rater ra=loadedRaters.get(k);
            ratersWithRatings.put(ra.getID(),ra.numRatings());
            
        
        }
        return ratersWithRatings;
    }
    
    public int getNumberOfRating(ArrayList<Rater> loadedRaters, String movieID){
        int numOfRatings = 0;
        
        for(Rater r: loadedRaters){
            if(r.hasRating(movieID)){
                numOfRatings+=1;
            }
        }
        return numOfRatings;
    }
    
    public HashMap<String,Integer> getmovieWithNumOfRater(ArrayList<Rater>loadedRaters, HashMap<String,Integer> movieWithNumOfRater){
        for(int k =0;k<loadedRaters.size();k++){
            Rater ra=loadedRaters.get(k);
            for(int i=0;i<ra.numRatings();i++){
                String movieId = ra.getItemsRated().get(i);
                
                if(movieWithNumOfRater.containsKey(movieId)){
                    movieWithNumOfRater.put(movieId,movieWithNumOfRater.get(movieId)+1);
                }else{
                    movieWithNumOfRater.put(movieId,1);
                    
                }
            
            }
            
        }
        
        
        return movieWithNumOfRater;
    }
        
    
}
