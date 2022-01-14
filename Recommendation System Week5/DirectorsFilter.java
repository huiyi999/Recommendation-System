
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (Huiyi Chen) 
 * @version (13/01/2022)
 */
public class DirectorsFilter implements Filter{
	private String myDirectors;
	
	public DirectorsFilter(String directors) {
		myDirectors = directors;
	}
	
	@Override
	public boolean satisfies(String id) {
	    String[] directorsList = myDirectors.split(",");
	    for(int i =0;i<directorsList.length;i++){
	        if (MovieDatabase.getDirector(id).indexOf(directorsList[i]) != -1) {
	            return true;
	        }
	    }
	    return false;
	}
}
