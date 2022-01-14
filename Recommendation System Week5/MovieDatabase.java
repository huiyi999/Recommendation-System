import java.util.*;
import org.apache.commons.csv.*;
import edu.duke.FileResource;


// All methods and fields in the class are static. 
// This means you'll be able to access methods in MovieDatabase 
// without using new to create objects, 
// but by calling methods like MovieDatabase.getMovie("0120915").
public class MovieDatabase {
    private static HashMap<String, Movie> ourMovies;  
    //stores movie information in a HashMap for fast lookup of movie information given a movie ID

    public static void initialize(String moviefile) {
        if (ourMovies == null) {
            ourMovies = new HashMap<String,Movie>();
            loadMovies(moviefile);
        }
    }

    // This method is called as a safety check with any of the other public methods 
    // to make sure there is movie data in the database. 
    private static void initialize() {
        if (ourMovies == null) {
            ourMovies = new HashMap<String,Movie>();
            loadMovies("ratedmoviesfull.csv");
        }
    }	

    // build the HashMap. 
    private static void loadMovies(String filename) {
        FirstRatings fr = new FirstRatings();
        ArrayList<Movie> list = fr.loadMovies(filename);
        for (Movie m : list) {
            ourMovies.put(m.getID(), m);
        }
    }

    public static boolean containsID(String id) {
        initialize();
        return ourMovies.containsKey(id);
    }

    public static int getYear(String id) {
        initialize();
        return ourMovies.get(id).getYear();
    }

    public static String getGenres(String id) {
        initialize();
        return ourMovies.get(id).getGenres();
    }

    public static String getTitle(String id) {
        initialize();
        return ourMovies.get(id).getTitle();
    }

    public static Movie getMovie(String id) {
        initialize();
        return ourMovies.get(id);
    }

    public static String getPoster(String id) {
        initialize();
        return ourMovies.get(id).getPoster();
    }

    public static int getMinutes(String id) {
        initialize();
        return ourMovies.get(id).getMinutes();
    }

    public static String getCountry(String id) {
        initialize();
        return ourMovies.get(id).getCountry();
    }

    public static String getDirector(String id) {
        initialize();
        return ourMovies.get(id).getDirector();
    }

    public static int size() {
        return ourMovies.size();
    }

    // This method returns an ArrayList of type String of movie IDs 
    // that match the filtering criteria.
    public static ArrayList<String> filterBy(Filter f) {
        initialize();
        ArrayList<String> list = new ArrayList<String>();
        for(String id : ourMovies.keySet()) {
            if (f.satisfies(id)) {
                list.add(id);
            }
        }
        
        return list;
    }

}
