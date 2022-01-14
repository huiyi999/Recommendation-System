
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (Huiyi Chen) 
 * @version (13/01/2022)
 */

import java.util.*;
public class MovieRunnerSimilarRatings {
    
    public void printAverageRatings(){

        FourthRatings fourthRatings = new FourthRatings ( "ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv"); 
        
        System.out.println("read data for " + RaterDatabase.size() +" raters");
        System.out.println("read data for " + MovieDatabase.size()+" movies");
        
        //int minimalRaters=50;  
        int minimalRaters=35;
        //int minimalRaters=12;
        ArrayList<Rating> myRatings = fourthRatings.getAverageRatings(minimalRaters);

        Collections.sort(myRatings);
        
        System.out.println("print the list of movies, one movie per line (print its rating first, followed by its title)");
        for(Rating rating:myRatings){
            System.out.println(rating.getValue () +" : "+MovieDatabase.getTitle(rating.getItem ()));
            
        }
        System.out.println("found " + myRatings.size() + " movies with at least " +minimalRaters +" ratings");
    }
    
    public void printAverageRatingsByYearAfterAndGenre(){
        FourthRatings fourthRatings = new FourthRatings ( "ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv"); 
        
        System.out.println("read data for " + RaterDatabase.size() +" raters");
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
        
        ArrayList<Rating> myRatings = fourthRatings.getAverageRatingsByFilter(minimalRaters,allFilters);

        Collections.sort(myRatings);
        
        System.out.println("print the list of movies, one movie per line (print its rating first, followed by its title)");
        for(Rating rating:myRatings){
            System.out.println(rating.getValue () +" : "+MovieDatabase.getYear(rating.getItem ())+" "+MovieDatabase.getTitle(rating.getItem ()));
            System.out.println("Genre(s) : " + MovieDatabase.getGenres(rating.getItem()));
        }        
        System.out.println( myRatings.size() + " movies matched");
    }
    
    public void printSimilarRatings(){
        FourthRatings fourthRatings = new FourthRatings ( "ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv"); 
        
        System.out.println("read data for " + RaterDatabase.size() +" raters");
        System.out.println("read data for " + MovieDatabase.size()+" movies"); 
        
        String id = "337";
        int numSimilarRaters=10;
        int minimalRaters=3;
        ArrayList<Rating> myRatings = fourthRatings.getSimilarRatings(id,numSimilarRaters,minimalRaters);        
       
        Collections.sort(myRatings);
        
        System.out.println("print the list of movies, one movie per line (print its rating first, followed by its title)");
        for(Rating rating:myRatings){
            System.out.println(MovieDatabase.getTitle(rating.getItem ()) +" : "+rating.getValue ());
        }        
        System.out.println( myRatings.size() + " movies found");        
        
        
    }
    
    public void printSimilarRatingsByGenre(){
        FourthRatings fourthRatings = new FourthRatings ( "ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv"); 
        
        System.out.println("read data for " + RaterDatabase.size() +" raters");
        System.out.println("read data for " + MovieDatabase.size()+" movies");    
        
        String id = "964";
        int numSimilarRaters=20;
        int minimalRaters=5;

        String genre = "Mystery"; 
        GenreFilter filter = new GenreFilter(genre);
        
        ArrayList<Rating> myRatings = fourthRatings.getSimilarRatingsByFilter(id,numSimilarRaters,minimalRaters,filter);        
       
        Collections.sort(myRatings);
        
        System.out.println("print the list of movies, one movie per line (print its rating first, followed by its title)");
        for(Rating rating:myRatings){
            System.out.println(MovieDatabase.getTitle(rating.getItem ()) +" : "+rating.getValue ());
            System.out.println("Genre : " + MovieDatabase.getGenres(rating.getItem()));
        }        
        System.out.println( myRatings.size() + " movies found");        
        
    }
    
    public void printSimilarRatingsByDirector(){
        FourthRatings fourthRatings = new FourthRatings ( "ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv"); 
        
        System.out.println("read data for " + RaterDatabase.size() +" raters");
        System.out.println("read data for " + MovieDatabase.size()+" movies");
        
        String id = "120";
        int numSimilarRaters=10;
        int minimalRaters=2;

        String director = "Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh"; 
        DirectorsFilter filter = new DirectorsFilter(director);
        
        ArrayList<Rating> myRatings = fourthRatings.getSimilarRatingsByFilter(id,numSimilarRaters,minimalRaters,filter);        
       
        Collections.sort(myRatings);
        
        System.out.println("print the list of movies, one movie per line (print its rating first, followed by its title)");
        for(Rating rating:myRatings){
            System.out.println(MovieDatabase.getTitle(rating.getItem ()) +" : "+rating.getValue ());
            System.out.println("Directors : " + MovieDatabase.getDirector(rating.getItem()));
        }        
        System.out.println( myRatings.size() + " movies found");         
    }
    
    
    public void printSimilarRatingsByGenreAndMinutes (){
        FourthRatings fourthRatings = new FourthRatings ( "ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv"); 
        
        System.out.println("read data for " + RaterDatabase.size() +" raters");
        System.out.println("read data for " + MovieDatabase.size()+" movies");
        
        String id = "168";
        int numSimilarRaters=10;
        int minimalRaters=3;

        int minMinutes = 80;
        int maxMinutes = 160; 
        MinutesFilter minutesFilter = new MinutesFilter (minMinutes, maxMinutes);
        
        String genre = "Drama"; 
        GenreFilter genreFilter = new GenreFilter(genre);
        
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(minutesFilter);
        allFilters.addFilter(genreFilter);
        ArrayList<Rating> myRatings = fourthRatings.getSimilarRatingsByFilter(id,numSimilarRaters,minimalRaters,allFilters);        
       
        Collections.sort(myRatings);
        
        System.out.println("print the list of movies, one movie per line (print its rating first, followed by its title)");
        for(Rating rating:myRatings){
            System.out.println(MovieDatabase.getTitle(rating.getItem ())+
            " Time: " + MovieDatabase.getMinutes(rating.getItem())+" : "+rating.getValue ());
            System.out.println("Genre : " + MovieDatabase.getGenres(rating.getItem()));
        }        
        System.out.println( myRatings.size() + " movies found");          
    }
    
    public void printSimilarRatingsByYearAfterAndMinutes(){
        FourthRatings fourthRatings = new FourthRatings ( "ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv"); 
        
        System.out.println("read data for " + RaterDatabase.size() +" raters");
        System.out.println("read data for " + MovieDatabase.size()+" movies");
        String id = "314";
        int numSimilarRaters=10;
        int minimalRaters=5;

        int year = 1975;
        YearAfterFilter yearFilter = new YearAfterFilter(year);
        
        int minMinutes = 70;
        int maxMinutes = 200; 
        MinutesFilter minutesFilter = new MinutesFilter (minMinutes, maxMinutes);

        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(minutesFilter);
        allFilters.addFilter(yearFilter);
        ArrayList<Rating> myRatings = fourthRatings.getSimilarRatingsByFilter(id,numSimilarRaters,minimalRaters,allFilters);        
       
        Collections.sort(myRatings);
        
        System.out.println("print the list of movies, one movie per line (print its rating first, followed by its title)");
        for(Rating rating:myRatings){
            System.out.println(MovieDatabase.getTitle(rating.getItem ())+" "+ MovieDatabase.getYear(rating.getItem ())+
            " Time: " + MovieDatabase.getMinutes(rating.getItem())+" : "+rating.getValue ());
        }        
        System.out.println( myRatings.size() + " movies found");         
        
    }
}
