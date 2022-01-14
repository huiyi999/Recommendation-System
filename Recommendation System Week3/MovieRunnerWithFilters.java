
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (Huiyi Chen) 
 * @version (13/01/2022)
 */

import java.util.*;
public class MovieRunnerWithFilters {
    public void printAverageRatings(){
        //SecondRatings secondRatings = new SecondRatings ("data/ratedmovies_short.csv", "data/ratings_short.csv");

        //ThirdRatings thirdRatings = new ThirdRatings ( "data/ratings_short.csv");
        //MovieDatabase.initialize("data/ratedmovies_short.csv");
        ThirdRatings thirdRatings = new ThirdRatings ( "data/ratings.csv");
        MovieDatabase.initialize("data/ratedmoviesfull.csv");
        
        System.out.println("read data for " + thirdRatings.getRaterSize() +" raters");
        System.out.println("read data for " + MovieDatabase.size()+" movies");
        
        //int minimalRaters=50;  
        int minimalRaters=35;
        //int minimalRaters=12;
        ArrayList<Rating> myRatings = thirdRatings.getAverageRatings(minimalRaters);

        Collections.sort(myRatings);
        
        System.out.println("print the list of movies, one movie per line (print its rating first, followed by its title)");
        for(Rating rating:myRatings){
            System.out.println(rating.getValue () +" : "+MovieDatabase.getTitle(rating.getItem ()));
            
        }
        System.out.println("found " + myRatings.size() + " movies with at least " +minimalRaters +" ratings");
    }
    
    public void printAverageRatingsByYear(){
        //ThirdRatings thirdRatings = new ThirdRatings ( "data/ratings_short.csv");
        //MovieDatabase.initialize("data/ratedmovies_short.csv");
        ThirdRatings thirdRatings = new ThirdRatings ( "data/ratings.csv");
        MovieDatabase.initialize("data/ratedmoviesfull.csv");
        
        System.out.println("read data for " + thirdRatings.getRaterSize() +" raters");
        System.out.println("read data for " + MovieDatabase.size()+" movies");
        
        //int minimalRaters=50;  
        int minimalRaters=20;
        //int minimalRaters=12;
        
        int year = 2000;
        YearAfterFilter yearFilter = new YearAfterFilter(year);
        ArrayList<Rating> myRatings = thirdRatings.getAverageRatingsByFilter(minimalRaters,yearFilter);

        Collections.sort(myRatings);
        
        System.out.println("print the list of movies, one movie per line (print its rating first, followed by its title)");
        for(Rating rating:myRatings){
            System.out.println(rating.getValue () +" : "+MovieDatabase.getYear(rating.getItem ())+" "+MovieDatabase.getTitle(rating.getItem ()));
            
        }
        System.out.println("found " + myRatings.size() + " movies with at least " +minimalRaters +" ratings after "+year);
    }
    
    public void printAverageRatingsByGenre(){
        //ThirdRatings thirdRatings = new ThirdRatings ( "data/ratings_short.csv");
        //MovieDatabase.initialize("data/ratedmovies_short.csv");
        ThirdRatings thirdRatings = new ThirdRatings ( "data/ratings.csv");
        MovieDatabase.initialize("data/ratedmoviesfull.csv");
        
        System.out.println("read data for " + thirdRatings.getRaterSize() +" raters");
        System.out.println("read data for " + MovieDatabase.size()+" movies");
        
        //int minimalRaters=50;  
        int minimalRaters=20;
        //int minimalRaters=12;
        
        String genre = "Comedy";
        GenreFilter genreFilter = new GenreFilter(genre);
        ArrayList<Rating> myRatings = thirdRatings.getAverageRatingsByFilter(minimalRaters,genreFilter);

        Collections.sort(myRatings);
        
        System.out.println("print the list of movies, one movie per line (print its rating first, followed by its title)");
        for(Rating rating:myRatings){
            System.out.println(rating.getValue () +" : "+MovieDatabase.getTitle(rating.getItem ()));
            System.out.println("Genre(s) : " + MovieDatabase.getGenres(rating.getItem()));
        }
        System.out.println("found " + myRatings.size() + " movies with at least " +minimalRaters +" ratings with "+genre);
    }
    
    public void printAverageRatingsByMinutes(){
        //ThirdRatings thirdRatings = new ThirdRatings ( "data/ratings_short.csv");
        //MovieDatabase.initialize("data/ratedmovies_short.csv");
        ThirdRatings thirdRatings = new ThirdRatings ( "data/ratings.csv");
        MovieDatabase.initialize("data/ratedmoviesfull.csv");
        
        System.out.println("read data for " + thirdRatings.getRaterSize() +" raters");
        System.out.println("read data for " + MovieDatabase.size()+" movies");
        
        //int minimalRaters=50;  
        int minimalRaters=5;
        //int minimalRaters=12;
        
        int minMinutes = 105; 
        int maxMinutes = 135; 
        MinutesFilter minutesFilter = new MinutesFilter (minMinutes, maxMinutes);
        ArrayList<Rating> myRatings = thirdRatings.getAverageRatingsByFilter(minimalRaters,minutesFilter);

        Collections.sort(myRatings);
        
        System.out.println("print the list of movies, one movie per line (print its rating first, followed by its title)");
        for(Rating rating:myRatings){
            System.out.println(rating.getValue() + " Time: " + MovieDatabase.getMinutes(rating.getItem())
            + " " + MovieDatabase.getTitle(rating.getItem()));
        }
        System.out.println("found " + myRatings.size() + " movies with at least " +minimalRaters +" ratings");
    }
    
    public void printAverageRatingsByDirectors(){
        //ThirdRatings thirdRatings = new ThirdRatings ( "data/ratings_short.csv");
        //MovieDatabase.initialize("data/ratedmovies_short.csv");
        ThirdRatings thirdRatings = new ThirdRatings ( "data/ratings.csv");
        MovieDatabase.initialize("data/ratedmoviesfull.csv");
        
        System.out.println("read data for " + thirdRatings.getRaterSize() +" raters");
        System.out.println("read data for " + MovieDatabase.size()+" movies");
        
        //int minimalRaters=50;  
        int minimalRaters=4;
        //int minimalRaters=12;
        
        String directors = "Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack";
        DirectorsFilter directorsFilter = new DirectorsFilter (directors);
        
        ArrayList<Rating> myRatings = thirdRatings.getAverageRatingsByFilter(minimalRaters,directorsFilter);

        Collections.sort(myRatings);
        
        System.out.println("print the list of movies, one movie per line (print its rating first, followed by its title)");
        for(Rating rating:myRatings){
            System.out.println(rating.getValue() + " : " + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("Directors : " + MovieDatabase.getDirector(rating.getItem()));
        }
        System.out.println("found " + myRatings.size() + " movies with at least " +minimalRaters +" ratings");
    }
    
    public void printAverageRatingsByYearAfterAndGenre(){
        //ThirdRatings thirdRatings = new ThirdRatings ( "data/ratings_short.csv");
        //MovieDatabase.initialize("data/ratedmovies_short.csv");
        ThirdRatings thirdRatings = new ThirdRatings ( "data/ratings.csv");
        MovieDatabase.initialize("data/ratedmoviesfull.csv");
        
        System.out.println("read data for " + thirdRatings.getRaterSize() +" raters");
        System.out.println("read data for " + MovieDatabase.size()+" movies");
        
        //int minimalRaters=50;  
        int minimalRaters=8;
        //int minimalRaters=12;
        
        int year = 1990;
        YearAfterFilter yearFilter = new YearAfterFilter(year);
        
        String genre = "Drama";
        GenreFilter genreFilter = new GenreFilter(genre);
        
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(yearFilter);
        allFilters.addFilter(genreFilter);
        
        ArrayList<Rating> myRatings = thirdRatings.getAverageRatingsByFilter(minimalRaters,allFilters);

        Collections.sort(myRatings);
        
        System.out.println("print the list of movies, one movie per line (print its rating first, followed by its title)");
        for(Rating rating:myRatings){
            System.out.println(rating.getValue () +" : "+MovieDatabase.getYear(rating.getItem ())+" "+MovieDatabase.getTitle(rating.getItem ()));
            System.out.println("Genre(s) : " + MovieDatabase.getGenres(rating.getItem()));
        }        
        System.out.println( myRatings.size() + " movies matched");
    }
    
    public void printAverageRatingsByDirectorsAndMinutes(){
        //ThirdRatings thirdRatings = new ThirdRatings ( "data/ratings_short.csv");
        //MovieDatabase.initialize("data/ratedmovies_short.csv");
        ThirdRatings thirdRatings = new ThirdRatings ( "data/ratings.csv");
        MovieDatabase.initialize("data/ratedmoviesfull.csv");
        
        System.out.println("read data for " + thirdRatings.getRaterSize() +" raters");
        System.out.println("read data for " + MovieDatabase.size()+" movies");
        
        //int minimalRaters=50;  
        int minimalRaters=3;
        //int minimalRaters=12;
        
        int minMinutes = 90; // variable
        int maxMinutes = 180; // variable
        MinutesFilter minutesFilter = new MinutesFilter (minMinutes, maxMinutes);        

        String directors = "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack";
        DirectorsFilter directorsFilter = new DirectorsFilter (directors);
        
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(minutesFilter);
        allFilters.addFilter(directorsFilter);
        
        ArrayList<Rating> myRatings = thirdRatings.getAverageRatingsByFilter(minimalRaters,allFilters);

        Collections.sort(myRatings);
        
        System.out.println("print the list of movies, one movie per line (print its rating first, followed by its title)");
        for(Rating rating:myRatings){
            System.out.println(rating.getValue () +" : "+MovieDatabase.getYear(rating.getItem ())+" "+MovieDatabase.getTitle(rating.getItem ()));
            System.out.println("Directors : " + MovieDatabase.getDirector(rating.getItem()));
        }
        System.out.println( myRatings.size() + " movies matched");
    }
}
