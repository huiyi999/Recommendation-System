
public interface Filter {
	public boolean satisfies(String id);
}
// The interface Filter has only one signature for the method satisfies. 
// Any filters that implement this interface must also have this method. 